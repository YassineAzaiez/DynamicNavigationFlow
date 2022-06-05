package com.skoove.app.presentation.screenCx

import androidx.navigation.fragment.findNavController
import com.skoove.app.R
import com.skoove.app.databinding.FragmentScreenbxBinding
import com.skoove.app.di.component.DependenciesInit
import com.skoove.app.presentation.ToolbarShared
import com.skoove.app.presentation.getChoicesList
import com.skoove.app.presentation.getOptionsList
import com.skoove.shared.baseui.BaseViewModelFragment
import com.skoove.shared.commun.extensions.hide
import com.skoove.shared.commun.extensions.loadColor
import com.skoove.shared.commun.extensions.observe
import com.skoove.shared.commun.extensions.show
import com.skoove.shared.commundomain.ChoicesModel
import com.skoove.shared.commundomain.ScreenBX

class ScreenCXFragment : BaseViewModelFragment<ScreenBxViewModel, FragmentScreenbxBinding>(
    ScreenBxViewModel::class.java,
    FragmentScreenbxBinding::inflate
) {

    private var validate = false
    override fun initViews() {
        DependenciesInit.appComponent().inject(this)
        ToolbarShared.getInstance().updateTitle(sharedPreferences.lastFetchExperiment)

    }






    override fun initObservers() {
        with(viewModel) {
            observe(onSubmitSelection) { isSubmitted ->
                if (isSubmitted.first) findNavController().navigate(isSubmitted.second)
            }
            observe(onError) {
                togglePopUp(it)
            }
        }
    }


}