package com.sopt.dive.core.data

import com.sopt.dive.core.data.datasource.AuthDataSource
import com.sopt.dive.core.data.datasource.OpenDataDataSource
import com.sopt.dive.core.data.datasource.UserDataSource
import com.sopt.dive.core.data.datasourceimpl.AuthDataSourceImpl
import com.sopt.dive.core.data.datasourceimpl.OpenDataDataSourceImpl
import com.sopt.dive.core.data.datasourceimpl.UserDataSourceImpl
import com.sopt.dive.core.data.repository.AuthRepository
import com.sopt.dive.core.data.repository.OpenDataRepository
import com.sopt.dive.core.data.repository.UserRepository
import com.sopt.dive.core.data.repositoryimpl.AuthRepositoryImpl
import com.sopt.dive.core.data.repositoryimpl.OpenDataRepositoryImpl
import com.sopt.dive.core.data.repositoryimpl.UserRepositoryImpl

object RepositoryProvider {

    private val authDataSource: AuthDataSource by lazy {
        AuthDataSourceImpl(ServicePool.authService)
    }

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(authDataSource)
    }

    private val userDataSource: UserDataSource by lazy {
        UserDataSourceImpl(ServicePool.userService)
    }

    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(userDataSource)
    }

    private val openDataSource: OpenDataDataSource by lazy {
        OpenDataDataSourceImpl(ServicePool.openDataService)
    }

    val openDataRepository: OpenDataRepository by lazy {
        OpenDataRepositoryImpl(openDataSource)
    }

}