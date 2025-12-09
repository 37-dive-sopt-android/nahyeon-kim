package com.sopt.dive.core.data.di

import com.sopt.dive.core.data.datasource.AuthDataSource
import com.sopt.dive.core.data.datasource.OpenDataDataSource
import com.sopt.dive.core.data.datasource.UserDataSource
import com.sopt.dive.core.data.datasourceimpl.FakeAuthDataSourceImpl
import com.sopt.dive.core.data.datasourceimpl.FakeUserDataSourceImpl
import com.sopt.dive.core.data.datasourceimpl.OpenDataDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindUserDataSource(fakeUserDataSourceImpl: FakeUserDataSourceImpl): UserDataSource

    @Binds
    @Singleton
    abstract fun bindAuthDataSource(fakeAuthDataSourceImpl: FakeAuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindOpenDataSource(openDataDataSourceImpl: OpenDataDataSourceImpl): OpenDataDataSource
}