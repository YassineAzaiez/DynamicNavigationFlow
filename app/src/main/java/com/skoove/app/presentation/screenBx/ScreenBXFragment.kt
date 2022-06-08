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
import com.skoove.shared.commundomain.ScreenDataModel

class ScreenBXFragment : BaseViewModelFragment<ScreenBxViewModel, FragmentScreenbxBinding>(
    ScreenBxViewModel::class.java,
    FragmentScreenbxBinding::inflate
) {
    private var validate = false
    private lateinit var selectedOption: ChoicesModel
    private var action = R.id.action_ScreenBXFragment_to_ScreenCXFragment
    override fun initViews() {
        DependenciesInit.appComponent().inject(this)
        if (sharedPreferences.getScreensInList().isNotEmpty())
            sharedPreferences.lastFetchExperiment = sharedPreferences.getScreensInList().last().screen

        sharedPreferences.restList()
        ToolbarShared.getInstance().updateTitle(sharedPreferences.lastFetchExperiment)
        disableDefaultBackPress(true)
        setUpViews()
    }

    private fun setUpViews() {

        with(binding) {
            when (sharedPreferences.lastFetchExperiment) {

                ScreenBX.SCREENB1.source -> {
                    root.setBackgroundColor(activity.loadColor(R.color.color_F9EBC8))
                    initOptions(getChoicesList())
                    rvOptions.show()
                    tvScreenB3Content.hide()
                }
                ScreenBX.SCREENB2.source -> {
                    root.setBackgroundColor(activity.loadColor(R.color.color_FFEE63))
                    initOptions(getOptionsList())
                    rvOptions.show()
                    tvScreenB3Content.hide()
                }

                ScreenBX.SCREENB3.source -> {
                    root.setBackgroundColor(activity.loadColor(R.color.color_ADE498))
                    rvOptions.hide()
                    tvScreenB3Content.show()
                    tvScreenB3Content.text = getString(R.string.screenB3Content)
                    sharedPreferences.putScreeInList(
                        ScreenDataModel(
                            sharedPreferences.lastFetchExperiment,
                            ChoicesModel("",false,false,R.string.screenB3Content)

                        )
                    )
                    validate = true

                }


            }
        }

        binding.ivNext.setOnClickListener {
            if ((validate && ::selectedOption.isInitialized) &&
                sharedPreferences.lastFetchExperiment != ScreenBX.SCREENB3.source
            )
                viewModel.submitSelection()
            else
                findNavController().navigate(action)
        }
    }

    private fun initOptions(options: List<ChoicesModel>) {
        binding.rvOptions.adapter = ChoicesAdapter(options) {
            validate = it.isChoiceChecked
            selectedOption = it
        }

    }


    override fun initObservers() {
        with(viewModel) {
            observe(onSubmitSelection) { isSubmitted ->
                if (isSubmitted && ::selectedOption.isInitialized) {
                    sharedPreferences.putScreeInList(
                        ScreenDataModel(
                            sharedPreferences.lastFetchExperiment,
                            selectedOption

                        )
                    )
                    findNavController().navigate(action)
                }
            }


            observe(onError) {
                togglePopUp(it)
            }
        }
    }


}