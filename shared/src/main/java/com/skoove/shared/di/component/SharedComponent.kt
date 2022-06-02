package com.skoove.shared.di.component

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.skoove.shared.commun.AppSharedPreferences
import com.skoove.shared.di.module.CoroutinesModule
import com.skoove.shared.di.module.NetworkModule
import com.skoove.shared.di.module.SharedModule
import com.skoove.shared.di.qualifier.ApplicationContext
import com.skoove.shared.di.qualifier.IoDispatcher
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedModule::class, CoroutinesModule::class, NetworkModule::class])
interface SharedComponent {

    @ApplicationContext
    fun context(): Context

    fun retrofit(): Retrofit

    fun gson(): Gson

    fun appSharedPref(): AppSharedPreferences



    @IoDispatcher
    fun ioDispatcher(): CoroutineDispatcher


    @Component.Factory
    interface SharedComponentFactory {
        fun create(@BindsInstance application: Application): SharedComponent
    }

}