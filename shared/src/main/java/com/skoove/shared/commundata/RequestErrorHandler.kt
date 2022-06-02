package com.skoove.shared.commundata

import com.skoove.shared.BuildConfig
import com.skoove.shared.commundomain.ApiResultException
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException

object NetworkErrorHandler {

    private const val errorMessage = "Error Occurred"

    fun getRequestError(throwable: Throwable): Exception {
        if (BuildConfig.DEBUG) Timber.e("getRequestError: $throwable")
        return when (throwable) {
            is IOException -> {
                ApiResultException.Connection(throwable, errorMessage)
            }
            is SocketTimeoutException -> {
                ApiResultException.Timeout(throwable, errorMessage)
            }
            is NullPointerException -> {
                ApiResultException.EmptyReturn(throwable, errorMessage)
            }
            else -> {
                ApiResultException.Unexpected(throwable, errorMessage)
            }
        }
    }

    fun getHttpError(message: String) = ApiResultException.HttpError(message.errorMessage())

    fun getCacheError(message: String) = ApiResultException.CacheError(message.errorMessage())

    fun getSharedPrefError(message: String) = ApiResultException.SharedPrefError(message.errorMessage())

    private fun String?.errorMessage() =
        if (isNullOrEmpty()) errorMessage else this

}