package com.skoove.shared.commun

import com.skoove.shared.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException



/**
 * extension function for Flow Class to emit loading state before the flow starts
 */
fun <T> Flow<ApiResult<T>>.onFlowStarts() =
        onStart {
            emit(ApiResult.Loading)
        }.catch {
            emit(ApiResult.Error(DataSourceException.Unexpected(R.string.errorUnexpectedMessage)))
        }

fun <T : Any> Flow<ApiResult<T>>.applyCommonSideEffects() =
    retryWhen { cause, attempt ->
        when {
            (cause is Exception && attempt < MAX_RETRIES) -> {
                delay(getBackoffDelay(attempt))
                true
            }
            else -> {
                false
            }
        }
    }



private const val MAX_RETRIES = 3L
private const val INITIAL_BACKOFF = 3000L
private fun getBackoffDelay(attempt: Long) = INITIAL_BACKOFF * (attempt + 1)
