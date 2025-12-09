package com.sopt.dive.core.data.datasourceimpl

import com.sopt.dive.core.data.datasource.AuthDataSource
import com.sopt.dive.core.data.dto.request.SignInRequestDto
import com.sopt.dive.core.data.dto.response.BaseResponseDto
import com.sopt.dive.core.data.dto.response.SignInResponseDto
import javax.inject.Inject

class FakeAuthDataSourceImpl @Inject constructor() : AuthDataSource {
    override suspend fun postSignIn(request: SignInRequestDto): BaseResponseDto<SignInResponseDto> {
        if (request.username == "gamzza" && request.password == "Potato1108!") {
            return BaseResponseDto(
                success = true,
                code = "200",
                message = "로그인 성공",
                data = SignInResponseDto(
                    userId = 12345L,
                    message = "환영합니다, ${request.username}님!"
                )
            )
        } else {
            return BaseResponseDto(
                success = false,
                code = "401",
                message = "아이디 또는 비밀번호가 올바르지 않습니다.",
                data = null
            )
        }
    }
}