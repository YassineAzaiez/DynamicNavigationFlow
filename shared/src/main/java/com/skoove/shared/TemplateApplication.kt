package com.skoove.shared

import android.app.Application
import com.skoove.shared.di.component.DaggerSharedComponent
import com.skoove.shared.di.component.SharedComponent
import timber.log.Timber

class TemplateApplication : Application() {
    companion object {
        lateinit var sharedComponent: SharedComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
        initTimber()
    }

    private fun initDI() {
        sharedComponent = DaggerSharedComponent.factory().create(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}