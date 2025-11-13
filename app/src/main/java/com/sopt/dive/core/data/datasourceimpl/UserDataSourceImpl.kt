package com.sopt.dive.core.data.datasourceimpl

import com.sopt.dive.core.data.datasource.UserDataSource
import com.sopt.dive.core.data.dto.request.SignUpRequestDto
import com.sopt.dive.core.data.dto.response.BaseResponseDto
import com.sopt.dive.core.data.dto.response.MemberResponseDto
import com.sopt.dive.core.data.service.UserService

class UserDataSourceImpl (
    private val userService: UserService
) : UserDataSource {
    override suspend fun postSignUp(request: SignUpRequestDto): BaseResponseDto<MemberResponseDto> =
        userService.postSignUp(request = request)

    override suspend fun getUser(id: Long): BaseResponseDto<MemberResponseDto> =
        userService.getUser(id = id)
}