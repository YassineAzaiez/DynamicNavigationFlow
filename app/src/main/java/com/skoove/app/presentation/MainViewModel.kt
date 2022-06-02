package com.skoove.app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skoove.app.domain.usecases.LoginUseCase
import com.skoove.shared.baseui.BaseViewModel
import com.skoove.shared.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : BaseViewModel(ioDispatcher) {

    private val _onLogin = MutableLiveData<String>()
    val onLogin: LiveData<String>
        get() = _onLogin
    fun login() { executeUseCase(loginUseCase) { _onLogin.postValue(it) } }
}