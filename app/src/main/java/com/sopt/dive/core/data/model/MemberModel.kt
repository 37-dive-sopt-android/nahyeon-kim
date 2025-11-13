package com.sopt.dive.core.data.model

import com.sopt.dive.core.data.dto.response.MemberResponseDto

data class MemberModel(
    val id: Long,
    val username: String,
    val name: String,
    val email: String,
    val age: Int,
    val status: String
)

fun MemberResponseDto.toModel() =
    MemberModel(
        id = this.id,
        username = this.username,
        name = this.name,
        email = this.email,
        age = this.age,
        status = this.status
    )
