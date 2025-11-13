package com.sopt.dive.core.data.model

import com.sopt.dive.core.data.dto.response.UserDto
import com.sopt.dive.core.data.dto.response.UserListResponseDto

data class UserListModel(
    val page: Int,
    val totalPages: Int,
    val users: List<UserModel>
)

data class UserModel(
    val id: Int,
    val name: String,
    val email: String,
    val avatarUrl: String
)

fun UserListResponseDto.toModel(): UserListModel =
    UserListModel(
        page = page,
        totalPages = totalPages,
        users = data.map { it.toModel() }
    )

fun UserDto.toModel(): UserModel =
    UserModel(
        id = id,
        name = "$firstName $lastName",
        email = email,
        avatarUrl = avatar
    )
