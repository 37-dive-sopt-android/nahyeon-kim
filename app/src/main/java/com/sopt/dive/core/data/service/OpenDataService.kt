package com.sopt.dive.core.data.service

import com.sopt.dive.core.data.dto.response.UserListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenDataService {
    @GET("/api/users")
    suspend fun getUsersList(
        @Query("page") page: Int
    ): UserListResponseDto
}
