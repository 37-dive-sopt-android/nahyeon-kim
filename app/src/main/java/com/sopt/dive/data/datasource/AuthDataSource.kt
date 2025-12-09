package com.sopt.dive.data.datasource

import com.sopt.dive.data.dto.request.SignInRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.SignInResponseDto

interface AuthDataSource {
    suspend fun postSignIn(
        request: SignInRequestDto
    ): BaseResponseDto<SignInResponseDto>
}
