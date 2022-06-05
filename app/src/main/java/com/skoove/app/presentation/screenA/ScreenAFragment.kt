package com.skoove.app.presentation.screenA


import androidx.navigation.fragment.findNavController
import com.skoove.app.databinding.FragmentScreenaBinding
import com.skoove.app.di.component.DependenciesInit
import com.skoove.app.presentation.redirectToScreenBX
import com.skoove.shared.baseui.BaseViewModelFragment
import com.skoove.shared.commun.extensions.observe


class ScreenAFragment : BaseViewModelFragment<ScreenAViewModel, FragmentScreenaBinding>(
    ScreenAViewModel::class.java,
    FragmentScreenaBinding::inflate
) {


    override fun initViews() {
        DependenciesInit.appComponent().inject(this)
        handleScreenRedirection()
    }

    private fun handleScreenRedirection(): Unit =
        if (sharedPreferences.lastFetchExperiment.isEmpty())
            viewModel.fetchExperiments()
        else
            navigateTo()

    override fun initObservers() {
        with(viewModel) {
            observe(onfetchExperiments) { screenName ->
                sharedPreferences.lastFetchExperiment = screenName
                navigateTo()

            }
            observe(onfetchExperiments) { togglePopUp(it) }
        }
    }

    private fun navigateTo() =  findNavController().redirectToScreenBX(sharedPreferences.lastFetchExperiment)


}