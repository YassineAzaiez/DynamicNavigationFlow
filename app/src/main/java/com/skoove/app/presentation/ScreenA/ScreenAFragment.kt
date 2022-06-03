package com.skoove.app.presentation.ScreenA


import androidx.navigation.fragment.findNavController
import com.skoove.app.R
import com.skoove.app.databinding.FragmentScreenaBinding
import com.skoove.app.di.component.DependenciesInit
import com.skoove.app.presentation.redirectTo
import com.skoove.shared.baseui.BaseViewModelFragment
import com.skoove.shared.commun.extensions.observe
import com.skoove.shared.commundomain.ScreenBX.*

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
            findNavController().redirectTo(sharedPreferences.lastFetchExperiment)

    override fun initObservers() {
        with(viewModel) {
            observe(onfetchExperiments) { screenName ->
                sharedPreferences.lastFetchExperiment = screenName
                findNavController().redirectTo(sharedPreferences.lastFetchExperiment)

            }
            observe(onfetchExperiments) { togglePopUp(it) }
        }
    }



}