package com.sopt.dive.data.datasource

import com.sopt.dive.data.dto.response.UserListResponseDto

interface OpenDataDataSource {
    suspend fun getUserList(
        page: Int
    ): UserListResponseDto
}
