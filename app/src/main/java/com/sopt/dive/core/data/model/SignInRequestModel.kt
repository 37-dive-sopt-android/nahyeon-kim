package com.sopt.dive.core.data.model

import com.sopt.dive.core.data.dto.request.SignInRequestDto

data class SignInRequestModel(
    val username: String,
    val password: String
)

fun SignInRequestModel.toDto() =
    SignInRequestDto(
        username = this.username,
        password = this.password
    )