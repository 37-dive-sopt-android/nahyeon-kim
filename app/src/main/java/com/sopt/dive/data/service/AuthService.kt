package com.sopt.dive.data.service

import com.sopt.dive.data.dto.request.SignInRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.SignInResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/login")
    suspend fun postSignIn(
        @Body request: SignInRequestDto
    ): BaseResponseDto<SignInResponseDto>
}