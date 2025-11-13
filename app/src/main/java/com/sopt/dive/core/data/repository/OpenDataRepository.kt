package com.sopt.dive.core.data.repository

import com.sopt.dive.core.data.model.UserListModel

interface OpenDataRepository {
    suspend fun getUserList(
        page: Int
    ): Result<UserListModel>
}
