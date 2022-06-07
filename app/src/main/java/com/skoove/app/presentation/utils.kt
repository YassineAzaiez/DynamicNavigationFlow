package com.skoove.app.presentation

import androidx.navigation.NavController
import com.skoove.app.R
import com.skoove.shared.commundomain.ChoicesModel
import com.skoove.shared.commundomain.ScreenBX.*

fun NavController.redirectToScreen(screenName: String) =
    when (screenName) {
        SCREENB2.source, SCREENB3.source, SCREENB1.source -> navigate(R.id.action_ScreenAFragment_to_ScreenBXFragment)
        SCREENB2.destination, SCREENB3.destination, SCREENB1.destination ->navigate(R.id.action_ScreenAFragment_to_ScreenCXFragment)
        else  -> navigate(R.id.action_ScreenCxFragment_to_ScreenDFragment)

    }



fun getChoicesList() =
    mutableListOf(
        ChoicesModel("Choice A", hasCheckBox = false, choiceText = R.string.screenB3Content),
        ChoicesModel("Choice B", hasCheckBox = false ,choiceText = R.string.screenB3Content),
        ChoicesModel("Choice C", hasCheckBox = false ,choiceText = R.string.screenB3Content),
        ChoicesModel("Choice D", hasCheckBox = false,choiceText = R.string.screenB3Content),
        ChoicesModel("Choice E", hasCheckBox = false,choiceText = R.string.screenB3Content),
    )

fun getOptionsList() =
    mutableListOf(
        ChoicesModel("Option A", hasCheckBox = true,choiceText = R.string.screenB3Content),
        ChoicesModel("Option B", hasCheckBox = true,choiceText = R.string.screenB3Content),
        ChoicesModel("Option C", hasCheckBox = true,choiceText = R.string.screenB3Content),
        ChoicesModel("Option D", hasCheckBox = true,choiceText = R.string.screenB3Content),
        ChoicesModel("Option E", hasCheckBox = true,choiceText = R.string.screenB3Content),
    )
