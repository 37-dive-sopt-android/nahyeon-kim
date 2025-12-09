package com.sopt.dive.data.di

import com.sopt.dive.data.service.AuthService
import com.sopt.dive.data.service.OpenDataService
import com.sopt.dive.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideUserService(
        @DefaultRetrofit retrofit: Retrofit
    ): UserService = retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideAuthService(
        @DefaultRetrofit retrofit: Retrofit
    ): AuthService = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideOpenDataService(
        @OpenRetrofit retrofit: Retrofit
    ): OpenDataService = retrofit.create(OpenDataService::class.java)
}