package devin.astra.pokeapi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import devin.astra.pokeapi.data.network.NetworkClient
import devin.astra.pokeapi.data.network.NetworkInterceptor
import devin.astra.pokeapi.data.sources.remote.PokeApiService
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideNetworkClient(interceptor: NetworkInterceptor): NetworkClient {
    val timeout = 30L
    val logging = NetworkInterceptor.createLogging()
    val builder = OkHttpClient.Builder().apply {
      addInterceptor(interceptor)
      addInterceptor(logging)
      callTimeout(timeout, TimeUnit.SECONDS)
      writeTimeout(timeout, TimeUnit.SECONDS)
      readTimeout(timeout, TimeUnit.SECONDS)
    }
    return NetworkClient(builder.build())
  }

  @Provides
  @Singleton
  fun providePokeApiService(client: NetworkClient): PokeApiService {
    return client.create(PokeApiService::class.java)
  }

}