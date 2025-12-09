package com.sopt.dive.data.service

import com.sopt.dive.BuildConfig
import com.sopt.dive.data.dto.response.UserListResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface OpenDataService {
    @GET("/api/users")
    suspend fun getUsersList(
        @Query("page") page: Int,
        @Header("x-api-key") apiKey: String = BuildConfig.OPEN_API_KEY
    ): UserListResponseDto
}
