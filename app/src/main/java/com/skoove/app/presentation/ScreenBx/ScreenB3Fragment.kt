package com.skoove.app.presentation.ScreenBx


import com.skoove.app.databinding.FragmentScreenaBinding
import com.skoove.app.di.component.DependenciesInit
import com.skoove.app.presentation.MainViewModel

import com.skoove.shared.baseui.BaseViewModelFragment
import com.skoove.shared.commun.extensions.observe

class ScreenB3Fragment : BaseViewModelFragment<MainViewModel, FragmentScreenaBinding>(MainViewModel::class.java,FragmentScreenaBinding::inflate) {



    override fun initViews() {
        DependenciesInit.appComponent().inject(this)
    }

    override fun initObservers() {
        with(viewModel) {
            observe(onLogin) {}
            observe(onError) { togglePopUp(it) }
        }
    }


}