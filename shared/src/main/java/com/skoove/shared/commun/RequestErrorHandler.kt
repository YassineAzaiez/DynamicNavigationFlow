package com.skoove.shared.commun


import com.skoove.shared.R
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object RequestErrorHandler {

    private const val HTTP_CODE_CLIENT_START = 400
    private const val HTTP_CODE_CLIENT_END = 499
    private const val HTTP_CODE_SERVER_START = 500
    private const val HTTP_CODE_SERVER_END = 599

    fun getRequestError(throwable: Throwable): DataSourceException {
        return when (throwable) {
            is HttpException -> {
                handleHttpException(throwable)
            }
            is IOException -> {
                DataSourceException.Connection(R.string.errorNetwork)
            }
            is SocketTimeoutException -> {

                DataSourceException.Timeout(R.string.errorUnexpectedMessage)
            }
            else -> {
                DataSourceException.Unexpected(R.string.errorUnexpectedMessage)
            }
        }
    }

    private fun handleHttpException(httpException: HttpException): DataSourceException {
        return when (httpException.code()) {
            in HTTP_CODE_CLIENT_START..HTTP_CODE_CLIENT_END -> {

                DataSourceException.Client(R.string.errorUnexpectedMessage)
            }
            in HTTP_CODE_SERVER_START..HTTP_CODE_SERVER_END -> {

                DataSourceException.Server(R.string.errorUnexpectedMessage)
            }
            else -> {
                DataSourceException.Unexpected(R.string.errorUnexpectedMessage)
            }
        }
    }
}
