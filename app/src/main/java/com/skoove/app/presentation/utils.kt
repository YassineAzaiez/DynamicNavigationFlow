package com.skoove.app.presentation

import androidx.navigation.NavController
import com.skoove.app.R
import com.skoove.shared.commundomain.ChoicesModel
import com.skoove.shared.commundomain.ScreenBX.*

fun NavController.redirectToScreenBX(screenName: String) =
    when (screenName) {
        SCREENB2.destination, SCREENB3.destination, SCREENB1.destination -> navigate(R.id.action_ScreenAFragment_to_ScreenBXFragment)
        else -> navigate(R.id.action_ScreenAFragment_to_ScreenC2Fragment2)

    }

fun NavController.redirectToScreenCX(screenName: String) =
    when (screenName) {
        SCREENB2.destination, SCREENB3.destination, SCREENB1.destination -> navigate(R.id.action_ScreenAFragment_to_ScreenBXFragment)
        else -> navigate(R.id.action_ScreenAFragment_to_ScreenC2Fragment2)

    }


fun getChoicesList() =
    mutableListOf(
        ChoicesModel("Choice A", hasPic = false, choiceText = R.string.screenB3Content),
        ChoicesModel("Choice B", hasPic = false ,choiceText = R.string.screenB3Content),
        ChoicesModel("Choice C", hasPic = false ,choiceText = R.string.screenB3Content),
        ChoicesModel("Choice D", hasPic = false,choiceText = R.string.screenB3Content),
        ChoicesModel("Choice E", hasPic = false,choiceText = R.string.screenB3Content),
    )

fun getOptionsList() =
    mutableListOf(
        ChoicesModel("Option A", hasPic = true,choiceText = R.string.screenB3Content),
        ChoicesModel("Option B", hasPic = true,choiceText = R.string.screenB3Content),
        ChoicesModel("Option C", hasPic = true,choiceText = R.string.screenB3Content),
        ChoicesModel("Option D", hasPic = true,choiceText = R.string.screenB3Content),
        ChoicesModel("Option E", hasPic = true,choiceText = R.string.screenB3Content),
    )
