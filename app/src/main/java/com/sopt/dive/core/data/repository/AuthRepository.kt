package com.sopt.dive.core.data.repository

import com.sopt.dive.core.data.model.SignInModel
import com.sopt.dive.core.data.model.SignInRequestModel

interface AuthRepository {
    suspend fun postSignIn(
        request: SignInRequestModel
    ): Result<SignInModel>
}