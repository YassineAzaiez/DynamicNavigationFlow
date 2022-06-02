package com.skoove.app.data.datasource

import com.skoove.shared.commun.ApiResult

interface AppRemoteDataSource {
    suspend fun login(): ApiResult<String>
    suspend fun fetchExperiments(): ApiResult<String>
    suspend fun submitSelection(): ApiResult<Unit?>
}