package com.sopt.dive.data.datasourceimpl

import com.sopt.dive.data.datasource.UserDataSource
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.BaseResponseDto
import com.sopt.dive.data.dto.response.MemberResponseDto
import javax.inject.Inject

class FakeUserDataSourceImpl @Inject constructor() : UserDataSource {
    private val fakeUsers = mutableMapOf<Long, MemberResponseDto>()
    private var currentId = 1L

    init {
        fakeUsers[12345L] = MemberResponseDto(
            id = 12345L,
            username = "gamzza",
            name = "감자",
            email = "gamzza@naveer.com",
            age = 25,
            status = "ACTIVE"
        )
    }

    override suspend fun postSignUp(request: SignUpRequestDto): BaseResponseDto<MemberResponseDto> {
        if (fakeUsers.values.any { it.username == request.username }) {
            return BaseResponseDto(
                success = false,
                code = "409",
                message = "이미 존재하는 사용자입니다.",
                data = null
            )
        }

        val newUser = MemberResponseDto(
            id = currentId++,
            username = request.username,
            name = request.name,
            email = request.email,
            age = request.age,
            status = "ACTIVE"
        )

        fakeUsers[newUser.id] = newUser

        return BaseResponseDto(
            success = true,
            code = "201",
            message = "회원가입 성공",
            data = newUser
        )
    }

    override suspend fun getUser(id: Long): BaseResponseDto<MemberResponseDto> {
        val user = fakeUsers[id]

        return if (user != null) {
            BaseResponseDto(
                success = true,
                code = "200",
                message = "조회 성공",
                data = user
            )
        } else {
            BaseResponseDto(
                success = false,
                code = "404",
                message = "사용자를 찾을 수 없습니다. (ID: $id)",
                data = null
            )
        }
    }
}