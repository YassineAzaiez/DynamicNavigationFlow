package com.skoove.shared.commundomain

import com.skoove.shared.R
import com.skoove.shared.commun.ApiResult
import com.skoove.shared.commun.DataSourceException
import com.skoove.shared.commun.RequestErrorHandler
import retrofit2.Response

suspend fun <T : Any> tryResult(call: suspend () -> Response<T>): Any = runCatching {
        val baseResponse = call()
        when {
            baseResponse.isSuccessful -> handleSuccessfulApiResponse(baseResponse)
            else -> Error(DataSourceException.Server(baseResponse.errorBody()))
        }
    }.getOrElse { throwable ->
        ApiResult.Error(RequestErrorHandler.getRequestError(throwable))
    }


private fun < T : Any> handleSuccessfulApiResponse(response: Response<T>): ApiResult<T> {
    val body = response.body()
    return when {
        body != null -> ApiResult.Success(body)
        else -> ApiResult.Error(
            DataSourceException.Unexpected(R.string.errorBodyNull),
        )
    }
}





