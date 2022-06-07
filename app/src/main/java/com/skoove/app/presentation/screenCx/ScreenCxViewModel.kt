package com.skoove.app.presentation.screenCx

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skoove.app.domain.usecases.LoginUseCase
import com.skoove.shared.baseui.BaseViewModel
import com.skoove.shared.commun.ApiResult
import com.skoove.shared.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScreenCxViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel(ioDispatcher) {
    private var loginScreenCx  :Job ? = null
    private val _onLogin = MutableLiveData<String>()
    val onLogin: LiveData<String> = _onLogin

    private val _onLoginCanceled = MutableLiveData<Boolean>()
    val onLoginCanceled: LiveData<Boolean> = _onLoginCanceled

    fun login() {
       loginScreenCx =  viewModelScope.launch(ioDispatcher) {
          delay(3000)
           loginUseCase().collect { response ->
               when (response) {
                   is ApiResult.Success -> {
                       onSuccess(response.data) { _onLogin.postValue(it) }
                   }
                   is ApiResult.Error ->
                       onFailure(response.exception.message)
               }
           }

       }
    }

    fun cancelLogin() {
        loginScreenCx?.let {
            it.cancel()
            _onLoginCanceled.postValue(it.isCancelled)

        }
    }
}