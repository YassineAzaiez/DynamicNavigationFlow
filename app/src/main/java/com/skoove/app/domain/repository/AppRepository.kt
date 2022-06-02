package com.skoove.app.domain.repository

import com.skoove.shared.commun.ApiResult
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun login(): Flow<ApiResult<String>>
    suspend fun fetchExperiments(): Flow<ApiResult<String>>
    suspend fun submitSelection(): Flow<ApiResult<Unit?>>

}