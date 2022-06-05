package com.skoove.shared.commun.extensions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.gson.Gson


fun Context.loadColor(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)


fun Context.loadDrawable(@DrawableRes drawable: Int) = ContextCompat.getDrawable(this, drawable)


fun <T> T.toStringHashMap(): HashMap<String, String> {
    val obj = Gson().toJson(this)
    return Gson().fromJson(obj, HashMap::class.java) as HashMap<String, String>
}

fun <T> T.toAnyHashMap(): HashMap<String, Any> {
    val obj = Gson().toJson(this)
    return Gson().fromJson(obj, HashMap::class.java) as HashMap<String, Any>
}

