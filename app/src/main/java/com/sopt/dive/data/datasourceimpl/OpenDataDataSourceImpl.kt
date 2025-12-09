package com.sopt.dive.data.datasourceimpl

import com.sopt.dive.data.datasource.OpenDataDataSource
import com.sopt.dive.data.dto.response.UserListResponseDto
import com.sopt.dive.data.service.OpenDataService
import javax.inject.Inject

class OpenDataDataSourceImpl @Inject constructor(
    private val openDataService: OpenDataService
) : OpenDataDataSource {
    override suspend fun getUserList(page: Int): UserListResponseDto =
        openDataService.getUsersList(page = page)
}
