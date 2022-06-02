package com.skoove.shared.commun

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import javax.inject.Singleton

const val KEY_SESSION_ID = "KEY_SESSION_ID"

@Singleton
class AppSharedPreferences(context: Context) {


    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(
            "", Context.MODE_PRIVATE
        )
    }

    var sessionId: String
        get() = prefs.value.getString(KEY_SESSION_ID, "")!!
        set(value) = prefs.value.edit { putString(KEY_SESSION_ID, value) }


}