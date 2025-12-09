package com.sopt.dive.data.repository

import com.sopt.dive.data.model.SignInModel
import com.sopt.dive.data.model.SignInRequestModel

interface AuthRepository {
    suspend fun postSignIn(
        request: SignInRequestModel
    ): Result<SignInModel>
}