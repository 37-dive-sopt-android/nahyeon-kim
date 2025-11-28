package com.sopt.dive.core.data.repositoryimpl

import com.sopt.dive.core.data.datasource.AuthDataSource
import com.sopt.dive.core.data.model.SignInModel
import com.sopt.dive.core.data.model.SignInRequestModel
import com.sopt.dive.core.data.model.toDto
import com.sopt.dive.core.data.model.toModel
import com.sopt.dive.core.data.repository.AuthRepository
import com.sopt.dive.core.util.suspendRunCatching

class AuthRepositoryImpl (
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun postSignIn(request: SignInRequestModel): Result<SignInModel> =
        suspendRunCatching {
            authDataSource.postSignIn(request = request.toDto()).data!!.toModel()
        }
    }