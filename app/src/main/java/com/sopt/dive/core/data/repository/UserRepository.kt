package com.sopt.dive.core.data.repository

import com.sopt.dive.core.data.model.MemberModel
import com.sopt.dive.core.data.model.SignUpRequestModel

interface UserRepository {
    suspend fun postSignUp(
        request: SignUpRequestModel
    ): Result<MemberModel>

    suspend fun getUser(
        id: Long
    ): Result<MemberModel>
}