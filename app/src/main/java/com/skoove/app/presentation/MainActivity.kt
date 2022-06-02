package com.skoove.app.presentation


import com.skoove.app.databinding.ActivityMainBinding
import com.skoove.app.di.component.DependenciesInit
import com.skoove.shared.baseui.BaseViewModelActivity
import com.skoove.shared.commun.extensions.addFragment

class MainActivity :
    BaseViewModelActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class.java) {

    override fun initViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initViews() {
        DependenciesInit.appComponent().inject(this)
        addFragment(MainFragment(), binding.mainFragmentContainer.id)
    }
}