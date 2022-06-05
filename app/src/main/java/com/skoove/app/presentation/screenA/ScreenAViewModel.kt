package com.skoove.app.presentation.screenA

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skoove.app.domain.usecases.FetchExperiments

import com.skoove.shared.baseui.BaseViewModel
import com.skoove.shared.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ScreenAViewModel @Inject constructor(
    private val fetchExperiments: FetchExperiments,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : BaseViewModel(ioDispatcher) {

    private val _onFetchExperiments = MutableLiveData<String>()
    val onfetchExperiments: LiveData<String>
        get() = _onFetchExperiments
    fun fetchExperiments() { executeUseCase(fetchExperiments) { _onFetchExperiments.postValue(it) } }
}