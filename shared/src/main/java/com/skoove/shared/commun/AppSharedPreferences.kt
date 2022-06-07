package com.skoove.shared.commun

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.skoove.shared.commundomain.ScreenDataModel
import javax.inject.Singleton

const val KEY_SESSION_ID = "KEY_SESSION_ID"
const val KEY_IS_FIRST_LAUNCH = "KEY_IS_FIRST_LAUNCH"
const val KEY_LAST_OPENED_SCREEN_LIST = "KEY_LAST_OPENED_SCREEN_LIST"
const val KEY_LAST_FETCH_EXPERIMENTS = "KEY_LAST_FETCH_EXPERIMENTS"

@Singleton
class AppSharedPreferences(context: Context) {


    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(
            "skoove_preefs", Context.MODE_PRIVATE
        )
    }
    private val gson = GsonBuilder().create()


    var sessionId: String
        get() = prefs.value.getString(KEY_SESSION_ID, "") ?: ""
        set(value) = prefs.value.edit { putString(KEY_SESSION_ID, value) }

    var lastFetchExperiment: String
        get() = prefs.value.getString(KEY_LAST_FETCH_EXPERIMENTS, "") ?: ""
        set(value) = prefs.value.edit { putString(KEY_LAST_FETCH_EXPERIMENTS, value) }

    var isFirstLaunch: Boolean
        get() = prefs.value.getBoolean(KEY_IS_FIRST_LAUNCH, true)
        set(value) = prefs.value.edit { putBoolean(KEY_IS_FIRST_LAUNCH, value) }


    fun getScreensInList(): MutableList<ScreenDataModel> {
        val value = prefs.value.getString(KEY_LAST_OPENED_SCREEN_LIST, null)
        val type = object : TypeToken<MutableList<ScreenDataModel>>() {}.type
        return gson.fromJson(value, type) ?:  mutableListOf()
    }
    fun restList() {
        val list = getScreensInList()
        list.clear()
        val inString = gson.toJson(list)
        prefs.value.edit().putString(KEY_LAST_OPENED_SCREEN_LIST, inString).apply()
    }

    fun clearFields() {
        lastFetchExperiment = " "
        restList()
    }

    fun putScreeInList(screen: ScreenDataModel) {
        val list = getScreensInList()
        list.add(screen)
        val inString = gson.toJson(list)
        prefs.value.edit().putString(KEY_LAST_OPENED_SCREEN_LIST, inString).apply()
    }


}