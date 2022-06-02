package com.skoove.shared.commun

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val exception: DataSourceException) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}