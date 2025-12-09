package com.sopt.dive.data.repository

import com.sopt.dive.data.model.MemberModel
import com.sopt.dive.data.model.SignUpRequestModel

interface UserRepository {
    suspend fun postSignUp(
        request: SignUpRequestModel
    ): Result<MemberModel>

    suspend fun getUser(
        id: Long
    ): Result<MemberModel>
}