package com.skoove.app.di.module

import com.skoove.app.api.AppApiService
import com.skoove.app.data.datasource.AppRemoteDataSource
import com.skoove.app.data.datasource.AppRemoteDataSourceImpl
import com.skoove.app.di.scope.AppScope
import com.skoove.shared.di.qualifier.IoDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
class DataSourcesModule {
    @AppScope
    @Provides
    fun provideAppRemoteDataSource(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        apiService: AppApiService
    ): AppRemoteDataSource = AppRemoteDataSourceImpl(ioDispatcher, apiService)


}