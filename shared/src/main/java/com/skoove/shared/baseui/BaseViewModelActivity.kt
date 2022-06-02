package com.skoove.shared.baseui

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.skoove.shared.commun.extensions.viewModelProvider
import com.skoove.shared.di.viewmodels.DaggerViewModelFactory
import javax.inject.Inject

abstract class BaseViewModelActivity<VM : BaseViewModel, B : ViewBinding>(private val modelClass: Class<VM>) :
    BaseActivity<B>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLoadingToggle()
        initObservers()
    }

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    open fun initObservers() {
        /**
         * we used the key word open instead od abstract to give the option
         * to the child class to implement the function only if they need it
         */
    }

    private fun initLoadingToggle() {
        viewModel.toggleLoading.observe(this, { toggleLoading(it!!) })
    }


    protected val viewModel: VM by lazy {
        viewModelProvider(
            viewModelFactory,
            modelClass.kotlin
        )
    }

}