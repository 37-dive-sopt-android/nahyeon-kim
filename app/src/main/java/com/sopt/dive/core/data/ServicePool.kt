package com.sopt.dive.core.data

import com.sopt.dive.core.data.service.AuthService
import com.sopt.dive.core.data.service.UserService

object ServicePool {
    val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }

    val userService: UserService by lazy {
        ApiFactory.create<UserService>()
    }
}