package com.skoove.app.di.component

import com.skoove.shared.TemplateApplication


object DependenciesInit {

    private var appComponent: AppComponent? = null
    fun appComponent(): AppComponent {
        if (appComponent == null)
            appComponent =
                DaggerAppComponent.factory().create(TemplateApplication.sharedComponent)
        return appComponent as AppComponent
    }
}