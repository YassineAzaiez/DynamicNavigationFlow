package com.skoove.shared.commun.extensions


import android.content.Context
import android.view.View
import android.widget.EditText
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat


/**
 * extension function that make any view visible
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * extension function that make any view invisible
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}


/**
 * extension function that hide any view (gone)
 */
fun View.hide() {
    visibility = View.GONE
}



