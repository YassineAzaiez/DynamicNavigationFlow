package com.skoove.app.di.module

import com.skoove.app.api.AppApiService
import com.skoove.app.data.datasource.AppRemoteDataSource
import com.skoove.app.data.repository.AppRepositoryImpl
import com.skoove.app.di.scope.AppScope
import com.skoove.app.domain.repository.AppRepository
import com.skoove.shared.di.qualifier.IoDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
class RepositoriesModule {

    @AppScope
    @Provides
    fun provideApi(retrofit: Retrofit): AppApiService = retrofit.create(AppApiService::class.java)


    @AppScope
    @Provides
    fun provideAppRepository(
        appRemoteDataSource: AppRemoteDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): AppRepository = AppRepositoryImpl(appRemoteDataSource, ioDispatcher)

}