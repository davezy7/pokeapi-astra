package devin.astra.pokeapi.data.network

import devin.astra.pokeapi.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkClient @Inject constructor(
  private val client: OkHttpClient
) {
  fun <T> create(defClass: Class<T>): T {
    val retrofit = Retrofit.Builder()
      .addConverterFactory(getConverter())
      .baseUrl(BuildConfig.BASE_URL)
      .client(client)
      .build()
    return retrofit.create(defClass)
  }

  private fun getConverter(): GsonConverterFactory {
    return GsonConverterFactory.create()
  }
}