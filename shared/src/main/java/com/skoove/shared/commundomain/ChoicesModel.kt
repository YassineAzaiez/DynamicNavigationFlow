package com.skoove.shared.commundomain

import java.io.Serializable

data class ChoicesModel(
    val response: String,
    var isChoiceChecked: Boolean = false,
    val hasPic: Boolean,
    val choiceText : Int
) : Serializable
