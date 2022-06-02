package com.skoove.shared.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.skoove.shared.commun.AppSharedPreferences
import com.skoove.shared.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SharedModule {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideApplication(appContext: Application): Context = appContext.applicationContext

    @Provides
    @Singleton
    fun providesGson(): Gson =
        GsonBuilder().apply {
            excludeFieldsWithoutExposeAnnotation()
            setPrettyPrinting()
        }.serializeNulls().create()

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): AppSharedPreferences =
        AppSharedPreferences(context)

}