package fr.sohayb.marvelapp.app.error

import java.io.IOException
import fr.sohayb.marvelapp.base.error.Error


data class WSError(
    val code: Int,
    override val message: String
) : IOException(message) {

    fun getErrorType(): WSErrorType {
        return when (code) {

            else -> WSErrorType.UNKNOWN
        }
    }
}

enum class WSErrorType(val message: Int): Error {
    NO_INTERNET_CONNECTION(4),
    UNKNOWN(3)
}