package com.sopt.dive.core.data.datasource

import com.sopt.dive.core.data.dto.request.SignInRequestDto
import com.sopt.dive.core.data.dto.response.BaseResponseDto
import com.sopt.dive.core.data.dto.response.SignInResponseDto

interface AuthDataSource {
    suspend fun postSignIn(
        request: SignInRequestDto
    ): BaseResponseDto<SignInResponseDto>
}
