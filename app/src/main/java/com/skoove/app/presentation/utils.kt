package com.skoove.app.presentation

import androidx.navigation.NavController
import com.skoove.app.R

fun NavController.redirectTo(screenName: String, ) =
    when (screenName) {
        com.skoove.shared.commundomain.ScreenBX.SCREENB1.destination -> navigate(R.id.action_ScreenAFragment_to_ScreenB1Fragment)
        com.skoove.shared.commundomain.ScreenBX.SCREENB2.destination -> navigate(R.id.action_ScreenAFragment_to_ScreenB2Fragment)
        com.skoove.shared.commundomain.ScreenBX.SCREENB3.destination -> navigate(R.id.action_ScreenAFragment_to_ScreenB3Fragment)
        else -> navigate(R.id.action_ScreenAFragment_to_ScreenC2Fragment2)

    }
