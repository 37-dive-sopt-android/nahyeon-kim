package com.sopt.dive.core.data.repositoryimpl

import com.sopt.dive.core.data.datasource.UserDataSource
import com.sopt.dive.core.data.model.MemberModel
import com.sopt.dive.core.data.model.SignUpRequestModel
import com.sopt.dive.core.data.model.toDto
import com.sopt.dive.core.data.model.toModel
import com.sopt.dive.core.data.repository.UserRepository
import com.sopt.dive.core.util.suspendRunCatching
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