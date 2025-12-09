package com.sopt.dive.data.datasourceimpl

import com.sopt.dive.data.datasource.UserDataSource
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.MemberResponseDto
import com.sopt.dive.data.service.UserService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserDataSource {
    override suspend fun postSignUp(request: SignUpRequestDto): BaseResponseDto<MemberResponseDto> =
        userService.postSignUp(request = request)

    override suspend fun getUser(id: Long): BaseResponseDto<MemberResponseDto> =
        userService.getUser(id = id)
}