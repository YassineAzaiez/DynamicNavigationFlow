package com.skoove.shared.commun

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import javax.inject.Singleton


@Singleton
class AppSharedPreferences(context: Context) {


  private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(
            "", Context.MODE_PRIVATE
        )
    }

    private val gson by lazy { GsonBuilder().create() }

    fun <T> putObject(key: String, y: T) {
        val inString = gson.toJson(y)
        prefs.value.edit().putString(key, inString).apply()
    }

    fun <T> getObject(key: String, c: Class<T>): T? {
        val value = prefs.value.getString(key, null)
        return if (value != null) {
            gson.fromJson(value, c)
        } else {
            null
        }
    }

    fun deleteObject(key: String) {
        prefs.value.edit().remove(key).apply()
    }

}