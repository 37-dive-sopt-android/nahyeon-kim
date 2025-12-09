package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.core.util.suspendRunCatching
import com.sopt.dive.data.datasource.OpenDataDataSource
import com.sopt.dive.data.model.UserListModel
import com.sopt.dive.data.model.toModel
import com.sopt.dive.data.repository.OpenDataRepository
import javax.inject.Inject

class OpenDataRepositoryImpl @Inject constructor(
    private val openDataDataSource: OpenDataDataSource
) : OpenDataRepository {
    override suspend fun getUserList(page: Int): Result<UserListModel> =
        suspendRunCatching {
            openDataDataSource.getUserList(page = page).toModel()
        }
}
