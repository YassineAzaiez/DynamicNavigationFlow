package com.skoove.app.presentation


import com.skoove.app.databinding.FragmentScreenaBinding
import com.skoove.shared.baseui.BaseViewModelFragment
import com.skoove.shared.commun.extensions.observe

class ScreenDFragment : BaseViewModelFragment<MainViewModel, FragmentScreenaBinding>(MainViewModel::class.java,FragmentScreenaBinding::inflate) {



    override fun initViews() {

    }

    override fun initObservers() {
        with(viewModel) {
            observe(onLogin) {}
            observe(onError) { togglePopUp(it) }
        }
    }


}