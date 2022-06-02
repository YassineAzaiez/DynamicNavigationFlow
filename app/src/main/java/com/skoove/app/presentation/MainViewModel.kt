package com.skoove.app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skoove.app.domain.usecases.LoginUseCase
import com.skoove.shared.baseui.BaseViewModel
import com.skoove.shared.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getUsersUseCase: LoginUseCase,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : BaseViewModel(ioDispatcher) {

    private val _onGetUsers = MutableLiveData<List<String>>()
    val onGetUsers: LiveData<List<String>>
        get() = _onGetUsers

//    fun users() { executeUseCase(getUsersUseCase) { _onGetUsers.postValue(it) } }
}