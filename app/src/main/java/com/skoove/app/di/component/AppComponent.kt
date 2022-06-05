package com.skoove.app.di.component

import com.skoove.app.di.module.DataSourcesModule
import com.skoove.app.di.module.RepositoriesModule
import com.skoove.app.di.module.ViewModelModule
import com.skoove.app.di.scope.AppScope
import com.skoove.app.presentation.LauncherActivity
import com.skoove.app.presentation.screenA.ScreenAFragment
import com.skoove.app.presentation.screenBx.ScreenBXFragment
import com.skoove.shared.di.component.SharedComponent
import dagger.Component

@AppScope
@Component(
    modules = [ViewModelModule::class, RepositoriesModule::class, DataSourcesModule::class],
    dependencies = [SharedComponent::class]
)
interface AppComponent {

    fun inject(launcherActivity: LauncherActivity)
    fun inject(screenAFragment: ScreenAFragment)
    fun inject(screenBXFragment: ScreenBXFragment)




    @Component.Factory
    interface AppComponentFactory {
        fun create(sharedComponent: SharedComponent): AppComponent
    }
}