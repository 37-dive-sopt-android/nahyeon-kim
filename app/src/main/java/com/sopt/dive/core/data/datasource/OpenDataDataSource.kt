package com.sopt.dive.core.data.datasource

import com.sopt.dive.core.data.dto.response.UserListResponseDto

interface OpenDataDataSource {
    suspend fun getUserList(
        page: Int
    ): UserListResponseDto
}
