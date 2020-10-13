package fr.sohayb.marvelapp.app.error

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class ErrorInterceptor(private val gson: Gson) : Interceptor {

    private object Tag {
        const val interceptorSuccessStart = 200
        const val interceptorSuccessEnd = 299
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code !in Tag.interceptorSuccessStart..Tag.interceptorSuccessEnd) {
            val error: WSError = try {
                gson.fromJson(response.body?.string(), WSError::class.java)
            } catch (e: Throwable) {
                WSError(
                    response.code,
                    response.message
                )
            }
            Timber.e("Error WS Interceptor: ${response.code} ${(error as? WSError)?.message} -> ${request.url}")
            throw if(error.code == 0) error.copy(code = response.code) else error
        }
        return response
    }
}