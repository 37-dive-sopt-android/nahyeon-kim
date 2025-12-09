package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.core.util.suspendRunCatching
import com.sopt.dive.data.datasource.AuthDataSource
import com.sopt.dive.data.model.SignInModel
import com.sopt.dive.data.model.SignInRequestModel
import com.sopt.dive.data.model.toDto
import com.sopt.dive.data.model.toModel
import com.sopt.dive.data.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun postSignIn(request: SignInRequestModel): Result<SignInModel> =
        suspendRunCatching {
            authDataSource.postSignIn(request = request.toDto()).data!!.toModel()
        }
}