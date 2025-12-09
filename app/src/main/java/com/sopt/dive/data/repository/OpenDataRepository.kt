package com.sopt.dive.data.repository

import com.sopt.dive.data.model.UserListModel

interface OpenDataRepository {
    suspend fun getUserList(
        page: Int
    ): Result<UserListModel>
}
