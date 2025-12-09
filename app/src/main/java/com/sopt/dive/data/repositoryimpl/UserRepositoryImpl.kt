package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.core.util.suspendRunCatching
import com.sopt.dive.data.datasource.UserDataSource
import com.sopt.dive.data.model.MemberModel
import com.sopt.dive.data.model.SignUpRequestModel
import com.sopt.dive.data.model.toDto
import com.sopt.dive.data.model.toModel
import com.sopt.dive.data.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun postSignUp(request: SignUpRequestModel): Result<MemberModel> =
        suspendRunCatching {
            userDataSource.postSignUp(request = request.toDto()).data!!.toModel()
        }

    override suspend fun getUser(id: Long): Result<MemberModel> =
        suspendRunCatching {
            userDataSource.getUser(id = id).data!!.toModel()
        }
}