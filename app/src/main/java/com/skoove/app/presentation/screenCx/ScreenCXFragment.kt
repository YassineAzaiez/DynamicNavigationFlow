package com.skoove.app.presentation.screenCx


import androidx.navigation.fragment.findNavController
import com.skoove.app.R
import com.skoove.app.databinding.FragmentScreencxBinding
import com.skoove.app.di.component.DependenciesInit
import com.skoove.app.presentation.ToolbarShared
import com.skoove.app.presentation.redirectToScreen

import com.skoove.shared.baseui.BaseViewModelFragment

import com.skoove.shared.commun.extensions.observe
import com.skoove.shared.commundomain.ScreenBX
import com.skoove.shared.commundomain.getScreenBxDestination


class ScreenCXFragment : BaseViewModelFragment<ScreenCxViewModel, FragmentScreencxBinding>(
    ScreenCxViewModel::class.java,
    FragmentScreencxBinding::inflate
) {
    private lateinit var screenCTitle: String
    override fun initViews() {
        DependenciesInit.appComponent().inject(this)
        screenCTitle = getScreenBxDestination(sharedPreferences.lastFetchExperiment)
        sharedPreferences.lastFetchExperiment = screenCTitle
        setUpViews()
        disableDefaultBackPress(true)
    }

    override fun onBackPressCustomAction() {
        viewModel.cancelLogin()
    }

    private fun setUpViews() {
        ToolbarShared.getInstance().updateTitle(screenCTitle)
        if(screenCTitle.isNotFromScreenB3())
        sharedPreferences.getScreensInList().last().also {
            with(binding) {

               tvScreenCxTitle.text = it.data.response
                tvScreenCxContent.text = getString(it.data.choiceText)
            }
        }
        viewModel.login()
    }


    override fun initObservers() {
        with(viewModel) {
            observe(onLogin) {
                findNavController().navigate(R.id.action_ScreenCxFragment_to_ScreenDFragment)
            }
            observe(onError) {
                togglePopUp(it)
            }

            observe(onLoginCanceled) { isCanceled ->
                if (isCanceled) findNavController().navigate(R.id.action_ScreenCXFragment_to_ScreenBXFragment)
            }
        }
    }

    private fun String.isNotFromScreenB3 () = this == ScreenBX.SCREENB1.destination
}