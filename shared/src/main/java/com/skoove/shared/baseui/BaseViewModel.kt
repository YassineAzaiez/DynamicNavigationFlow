package com.skoove.shared.baseui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skoove.shared.commun.ApiResult
import com.skoove.shared.commun.ApiResult.Success
import com.skoove.shared.commundomain.BaseUseCase
import com.skoove.shared.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _toggleLoading = MutableLiveData<Boolean>()
    val toggleLoading: LiveData<Boolean> = _toggleLoading

    private val _onError = MutableLiveData<String>()
    val onError: LiveData<String> = _onError

    private fun showError(message: String?) {
        _onError.postValue( message)
    }

    private fun showLoadingToggle(visible: Boolean) = _toggleLoading.postValue(visible)

    private fun onFailure(msg: String?) {
        showLoadingToggle(false)
        showError(msg)
    }

    private fun <T> onSuccess(item: T, onAction: (T) -> Unit) {
        showLoadingToggle(false)
        onAction(item)
    }

    protected fun <T> executeUseCase(useCase: BaseUseCase<Flow<ApiResult<T>>>, onResult: (T) -> Unit) {
        showLoadingToggle(true)
        viewModelScope.launch(ioDispatcher) {
            useCase().collect { response ->
                when (response) {
                    is Success -> { onSuccess(response.data) { onResult(it) } }
                    is ApiResult.Error -> onFailure(response.exception.message)
                }
            }

        }
    }

}