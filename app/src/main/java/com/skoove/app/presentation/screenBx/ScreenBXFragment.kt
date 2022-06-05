package com.skoove.app.presentation.screenBx

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

class ScreenBXFragment : BaseViewModelFragment<ScreenBxViewModel, FragmentScreenbxBinding>(
    ScreenBxViewModel::class.java,
    FragmentScreenbxBinding::inflate
) {
    private var validate = false
    private var action = -1
    override fun initViews() {
        DependenciesInit.appComponent().inject(this)
        ToolbarShared.getInstance().updateTitle(sharedPreferences.lastFetchExperiment)
        setUpViews()
    }

    private fun setUpViews() {

        with(binding) {
            when (sharedPreferences.lastFetchExperiment) {
                ScreenBX.SCREENB1.destination -> {
                    root.setBackgroundColor(activity.loadColor(R.color.color_F9EBC8))
                    initOptions(getChoicesList())
                    rvOptions.show()
                    action = R.id.action_ScreenB1Fragment_to_ScreenC1Fragment
                    tvScreenB3Content.hide()
                }
                ScreenBX.SCREENB2.destination -> {
                    root.setBackgroundColor(activity.loadColor(R.color.color_FFEE63))
                    initOptions(getOptionsList())
                    rvOptions.show()
                    action = R.id.action_ScreenB2Fragment_to_ScreenC2Fragment
                    tvScreenB3Content.hide()
                }

                ScreenBX.SCREENB3.destination -> {
                    root.setBackgroundColor(activity.loadColor(R.color.color_ADE498))
                    rvOptions.hide()
                    tvScreenB3Content.show()
                    validate = true
                    action = R.id.action_ScreenB2Fragment_to_ScreenC2Fragment
                    tvScreenB3Content.text = getString(R.string.screenB3Content)
                }


            }
        }

        binding.ivNext.setOnClickListener {
            if(validate) viewModel.submitSelection(action)
        }
    }

    private fun initOptions(options: List<ChoicesModel>) {
        binding.rvOptions.adapter = ChoicesAdapter(options) {
            validate = it.isChoiceChecked
        }

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