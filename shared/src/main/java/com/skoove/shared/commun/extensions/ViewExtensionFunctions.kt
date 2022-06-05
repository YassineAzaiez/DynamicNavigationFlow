package com.skoove.shared.commun.extensions


import android.view.View
import android.widget.EditText


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

fun EditText.value() = text.toString().trim()

