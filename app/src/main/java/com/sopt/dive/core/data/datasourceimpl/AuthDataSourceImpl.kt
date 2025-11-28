package com.sopt.dive.core.data.datasourceimpl

import com.sopt.dive.core.data.datasource.AuthDataSource
import com.sopt.dive.core.data.dto.request.SignInRequestDto
import com.sopt.dive.core.data.dto.response.BaseResponseDto
import com.sopt.dive.core.data.dto.response.SignInResponseDto
import com.sopt.dive.core.data.service.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthDataSource {
    override suspend fun postSignIn(request: SignInRequestDto): BaseResponseDto<SignInResponseDto> =
        authService.postSignIn(request = request)
}