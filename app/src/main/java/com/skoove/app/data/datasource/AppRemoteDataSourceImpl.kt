package com.skoove.app.data.datasource

import com.skoove.app.api.AppApiService
import com.skoove.shared.commun.ApiResult
import com.skoove.shared.commun.DataSourceException
import com.skoove.shared.commun.RequestErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AppRemoteDataSourceImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val apiService: AppApiService
) : AppRemoteDataSource {
    override suspend fun login(): ApiResult<String> = withContext(ioDispatcher) {
        try {
            val result = apiService.login()
            if (result.isSuccessful) {
                ApiResult.Success(result.body()?:"")
            } else {
                ApiResult.Error(DataSourceException.Server(result.errorBody()))
            }
        } catch (e: Exception) {
            ApiResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun fetchExperiments(): ApiResult<String> = withContext(ioDispatcher) {
        try {
            val result = apiService.fetchExperiments()
            if (result.isSuccessful) {
                ApiResult.Success(result.body() ?: "")
            } else {
                ApiResult.Error(DataSourceException.Server(result.errorBody()))
            }
        } catch (e: Exception) {
            ApiResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun submitSelection(): ApiResult<Unit?>  = withContext(ioDispatcher) {
        try {
            val result = apiService.submitSelection()
            if (result.isSuccessful) {
                ApiResult.Success(result.body())
            } else {
                ApiResult.Error(DataSourceException.Server(result.errorBody()))
            }
        } catch (e: Exception) {
            ApiResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }
}