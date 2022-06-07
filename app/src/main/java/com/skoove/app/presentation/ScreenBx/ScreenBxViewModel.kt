package com.skoove.app.presentation.screenBx

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skoove.app.domain.usecases.SubmitSelection
import com.skoove.shared.baseui.BaseViewModel
import com.skoove.shared.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ScreenBxViewModel @Inject constructor(
    private val submitSelection: SubmitSelection,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : BaseViewModel(ioDispatcher) {

    private val _onSubmitSelection = MutableLiveData<Boolean>()
    val onSubmitSelection: LiveData<Boolean>
        get() = _onSubmitSelection
    fun submitSelection() { executeUseCase(submitSelection) { _onSubmitSelection.postValue(it) } }
}