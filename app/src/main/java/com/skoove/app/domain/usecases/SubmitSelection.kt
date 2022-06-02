package com.skoove.app.domain.usecases


import com.skoove.app.domain.repository.AppRepository
import com.skoove.shared.commun.ApiResult
import com.skoove.shared.commundomain.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubmitSelection @Inject constructor(private val appRepository: AppRepository) :
    BaseUseCase<Flow<ApiResult<Unit?>>> {
    override suspend fun invoke() = appRepository.submitSelection()
}