package com.sopt.dive.core.data.datasourceimpl

import com.sopt.dive.core.data.datasource.OpenDataDataSource
import com.sopt.dive.core.data.dto.response.UserListResponseDto
import com.sopt.dive.core.data.service.OpenDataService
import javax.inject.Inject

class OpenDataDataSourceImpl @Inject constructor(
    private val openDataService: OpenDataService
) : OpenDataDataSource {
    override suspend fun getUserList(page: Int): UserListResponseDto =
        openDataService.getUsersList(page = page)
}
