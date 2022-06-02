package com.skoove.app.data.repository

import com.skoove.app.data.datasource.AppRemoteDataSource
import com.skoove.app.domain.repository.AppRepository
import com.skoove.shared.commun.ApiResult
import com.skoove.shared.commun.applyCommonSideEffects
import com.skoove.shared.commun.onFlowStarts
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class AppRepositoryImpl(
    private val appRemoteDataSource: AppRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : AppRepository {
    override suspend fun login(): Flow<ApiResult<String>> = flow {
        appRemoteDataSource.login().run {
            when (this) {
                is ApiResult.Success -> {
                    emit(ApiResult.Success(data))
                }

                is ApiResult.Error -> {
                    emit(ApiResult.Error(exception))
                }
            }
        }
    }
        .onFlowStarts()
        .applyCommonSideEffects()
        .flowOn(ioDispatcher)

    override suspend fun fetchExperiments(): Flow<ApiResult<String>> = flow {
        appRemoteDataSource.fetchExperiments().run {
            when (this) {
                is ApiResult.Success -> {
                    emit(ApiResult.Success(data))
                }

                is ApiResult.Error -> {
                    emit(ApiResult.Error(exception))
                }
            }
        }
    }
        .onFlowStarts()
        .flowOn(ioDispatcher)

    override suspend fun submitSelection() = flow {
        appRemoteDataSource.submitSelection().run {
            when (this) {
                is ApiResult.Success -> {
                    emit(ApiResult.Success(data))
                }

                is ApiResult.Error -> {
                    emit(ApiResult.Error(exception))
                }
            }
        }
    }
        .flowOn(ioDispatcher)


}