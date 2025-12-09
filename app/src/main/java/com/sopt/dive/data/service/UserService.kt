package com.sopt.dive.data.service

import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.MemberResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("/api/v1/users")
    suspend fun postSignUp(
        @Body request: SignUpRequestDto
    ): BaseResponseDto<MemberResponseDto>

    @GET("/api/v1/users/{id}")
    suspend fun getUser(
        @Path("id") id: Long
    ): BaseResponseDto<MemberResponseDto>
}