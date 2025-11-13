package com.sopt.dive.core.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    @SerialName("userId")
    val userId: Long,
    @SerialName("message")
    val message: String
)