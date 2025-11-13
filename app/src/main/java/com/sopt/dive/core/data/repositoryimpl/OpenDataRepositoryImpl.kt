package com.sopt.dive.core.data.repositoryimpl

import com.sopt.dive.core.data.datasource.OpenDataDataSource
import com.sopt.dive.core.data.model.UserListModel
import com.sopt.dive.core.data.model.toModel
import com.sopt.dive.core.data.repository.OpenDataRepository
import com.sopt.dive.core.util.suspendRunCatching

class OpenDataRepositoryImpl(
    private val openDataDataSource: OpenDataDataSource
) : OpenDataRepository {
    override suspend fun getUserList(page: Int): Result<UserListModel> =
        suspendRunCatching {
            openDataDataSource.getUserList(page = page).toModel()
        }
}
