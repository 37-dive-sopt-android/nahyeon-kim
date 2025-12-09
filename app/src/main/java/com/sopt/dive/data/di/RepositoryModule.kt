package com.sopt.dive.data.di

import com.sopt.dive.data.repository.AuthRepository
import com.sopt.dive.data.repository.OpenDataRepository
import com.sopt.dive.data.repository.UserRepository
import com.sopt.dive.data.repositoryimpl.AuthRepositoryImpl
import com.sopt.dive.data.repositoryimpl.OpenDataRepositoryImpl
import com.sopt.dive.data.repositoryimpl.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindAuthDataRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindOpenDataRepository(openDataRepositoryImpl: OpenDataRepositoryImpl): OpenDataRepository
}
