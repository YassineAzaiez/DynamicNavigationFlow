package com.skoove.shared.baseui

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.skoove.shared.commun.extensions.viewModelProvider
import com.skoove.shared.di.viewmodels.DaggerViewModelFactory
import javax.inject.Inject

abstract class BaseViewModelFragment<VM : BaseViewModel,B : ViewBinding>(private val modelClass: Class<VM>,inflate: Inflate<B>) :
    BaseFragment<B>(inflate) {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory
    open fun initObservers() {
        /**
         * we used the key word open instead of abstract to give the option
         * to the child class to implement the function only if they need it
         */
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initLoadingToggle()
    }


    private fun initLoadingToggle() {
        viewModel.toggleLoading.observe(viewLifecycleOwner) {
            toggleLoading(it!!)
        }
    }

    protected val viewModel: VM by lazy {
        viewModelProvider(
            this.viewModelFactory,
            modelClass.kotlin
        )
    }

}