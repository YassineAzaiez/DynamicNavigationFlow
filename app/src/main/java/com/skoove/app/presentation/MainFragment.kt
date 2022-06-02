package com.skoove.app.presentation

import com.skoove.app.databinding.FragmentMainBinding
import com.skoove.app.di.component.DependenciesInit

import com.skoove.shared.baseui.BaseViewModelFragment
import com.skoove.shared.commun.extensions.observe

class MainFragment : BaseViewModelFragment<MainViewModel, FragmentMainBinding>(MainViewModel::class.java,FragmentMainBinding::inflate) {



    override fun initViews() {
        DependenciesInit.appComponent().inject(this)
       // viewModel.users()
    }

    override fun initObservers() {
        with(viewModel) {
            observe(onGetUsers) {  }
            observe(onError) { togglePopUp(it) }
        }
    }


}