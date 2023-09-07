package devin.astra.pokeapi.data.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class NetworkInterceptor @Inject constructor() : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()

    val modified = original.newBuilder().apply {
      addHeader("Content-Type", "application/json")
      method(original.method, original.body)
    }
    val request: Request = modified.build()
    return chain.proceed(request)
  }

  companion object {
    fun createLogging(): HttpLoggingInterceptor {
      val logging = HttpLoggingInterceptor()
      logging.level = HttpLoggingInterceptor.Level.BODY
      return logging
    }
  }
}