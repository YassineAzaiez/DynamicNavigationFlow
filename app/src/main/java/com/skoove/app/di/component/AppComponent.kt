package com.skoove.app.di.component

import com.skoove.app.di.module.DataSourcesModule
import com.skoove.app.di.module.RepositoriesModule
import com.skoove.app.di.module.ViewModelModule
import com.skoove.app.di.scope.AppScope
import com.skoove.app.presentation.LauncherActivity
import com.skoove.app.presentation.ScreenA.ScreenAFragment
import com.skoove.app.presentation.ScreenBx.ScreenB1Fragment
import com.skoove.app.presentation.ScreenBx.ScreenB2Fragment
import com.skoove.app.presentation.ScreenBx.ScreenB3Fragment
import com.skoove.app.presentation.ScreenCx.ScreenC1Fragment
import com.skoove.app.presentation.ScreenCx.ScreenC2Fragment
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
    fun inject(screenB1Fragment: ScreenB1Fragment)
    fun inject(screenB2Fragment: ScreenB2Fragment)
    fun inject(screenB3Fragment: ScreenB3Fragment)
    fun inject(screenC1Fragment: ScreenC1Fragment)
    fun inject(screenC2Fragment: ScreenC2Fragment)


    @Component.Factory
    interface AppComponentFactory {
        fun create(sharedComponent: SharedComponent): AppComponent
    }
}