package com.sopt.dive.data.datasource

import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.MemberResponseDto

interface UserDataSource {
    suspend fun postSignUp(
        request: SignUpRequestDto
    ): BaseResponseDto<MemberResponseDto>

    suspend fun getUser(
        id: Long
    ): BaseResponseDto<MemberResponseDto>
}