package com.skoove.app.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skoove.app.presentation.MainViewModel
import com.skoove.app.presentation.ScreenA.ScreenAViewModel
import com.skoove.shared.di.annotations.ViewModelKey
import com.skoove.shared.di.viewmodels.DaggerViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainVM(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScreenAViewModel::class)
    abstract fun bindScreenAVM(screenAViewModel: ScreenAViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}