package com.sopt.dive.core.data.model

import com.sopt.dive.core.data.dto.response.SignInResponseDto

data class SignInModel(
    val userId: Long,
    val message: String
)

fun SignInResponseDto.toModel() =
    SignInModel(
        userId = this.userId,
        message = this.message
    )