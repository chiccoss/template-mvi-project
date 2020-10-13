package fr.sohayb.marvelapp.app.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import fr.sohayb.marvelapp.app.error.WSError
import fr.sohayb.marvelapp.app.error.WSErrorType
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class ResponseHandler(private val context: Context) {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.Success(data)
    }

    fun handleException(e: Throwable): Resource<Nothing> {
        return Resource.Error(
            getErrorType(
                e
            )
        )
    }

    private fun getErrorType(error: Throwable): WSErrorType {
        return if (error is WSError) {
            Timber.e("Error WS Known: ${error.code} -> ${error.message}")
            error.getErrorType()
        }
        else if (!isConnecting() || error is UnknownHostException || error is SocketTimeoutException) {
            Timber.e("Error WS connection: No Connection Internet")
            WSErrorType.NO_INTERNET_CONNECTION
        } else {
            Timber.e("Error WS Unknown: ${error.localizedMessage}")
            WSErrorType.UNKNOWN
        }
    }

    private fun isConnecting(): Boolean {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                it.getNetworkCapabilities(it.activeNetwork)?.let {networkCapabilities ->
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkCapabilities.hasTransport(
                        NetworkCapabilities.TRANSPORT_CELLULAR
                    )
                } ?: false
            } else {
                it.activeNetworkInfo?.let {networkInfo ->
                    networkInfo.isConnected && (networkInfo.type == ConnectivityManager.TYPE_WIFI || networkInfo.type == ConnectivityManager.TYPE_MOBILE)
                } ?: false
            }
        } ?: false
    }
}

sealed class Resource<out T> {
    data class Success<out T>(val data: T): Resource<T>()
    data class Error(val error: WSErrorType): Resource<Nothing>()
}