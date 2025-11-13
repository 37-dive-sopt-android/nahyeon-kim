package com.sopt.dive.core.data.datasource

import com.sopt.dive.core.data.dto.request.SignUpRequestDto
import com.sopt.dive.core.data.dto.response.BaseResponseDto
import com.sopt.dive.core.data.dto.response.MemberResponseDto

interface UserDataSource {
    suspend fun postSignUp(
        request: SignUpRequestDto
    ): BaseResponseDto<MemberResponseDto>

    suspend fun getUser(
        id: Long
    ): BaseResponseDto<MemberResponseDto>
}