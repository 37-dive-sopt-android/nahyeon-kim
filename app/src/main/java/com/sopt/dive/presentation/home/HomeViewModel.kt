package com.sopt.dive.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.data.UserPreferences
import com.sopt.dive.core.data.model.UserModel
import com.sopt.dive.core.data.repository.OpenDataRepository
import com.sopt.dive.core.data.repository.UserRepository
import com.sopt.dive.core.util.UiState
import com.sopt.dive.presentation.home.model.ProfileActionType
import com.sopt.dive.presentation.home.model.ProfileBadge
import com.sopt.dive.presentation.home.model.ProfileDescription
import com.sopt.dive.presentation.home.model.ProfileItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val openDataRepository: OpenDataRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<HomeUiState>>(UiState.Empty)
    val uiState: StateFlow<UiState<HomeUiState>> = _uiState.asStateFlow()

    fun loadUserInfo() {
        val userId = userPreferences.getUserId()
        loadUserInfoById(userId)
    }

    private fun loadUserInfoById(userId: Long) {
        viewModelScope.launch {
            _uiState.update { UiState.Loading }

            userRepository.getUser(userId)
                .onSuccess { memberModel ->
                    loadUserListAndUpdate(memberModel.name)
                }
                .onFailure {
                    _uiState.update { UiState.Failure }
                }
        }
    }

    private suspend fun loadUserListAndUpdate(currentUserName: String) {
        openDataRepository.getUserList(page = 2)
            .onSuccess { userList ->
                val profileItems = userList.users.map { it.toProfileItemModel() }

                if (profileItems.isNotEmpty()) {
                    _uiState.update {
                        UiState.Success(
                            HomeUiState(
                                name = currentUserName,
                                profileItems = profileItems
                            )
                        )
                    }
                } else {
                    _uiState.update { UiState.Failure }
                }
            }
            .onFailure {
                _uiState.update { UiState.Failure }
            }
    }

    private fun UserModel.toProfileItemModel(): ProfileItemModel {
        val badge = when (id % 3) {
            0 -> ProfileBadge.BIRTHDAY
            1 -> ProfileBadge.MEMORIAL
            else -> ProfileBadge.NONE
        }

        val description = when (id % 4) {
            0 -> ProfileDescription.Exists("오늘 생일이에요! 🎉")
            1 -> ProfileDescription.Exists("항상 그리워요.")
            2 -> ProfileDescription.Exists("요즘 산책이 좋아요 🌤️")
            else -> ProfileDescription.None
        }

        val actionType = when (id % 4) {
            0 -> ProfileActionType.Music("Super Shy - NewJeans")
            1 -> ProfileActionType.Gift
            2 -> ProfileActionType.Music("Love Lee - AKMU")
            else -> ProfileActionType.None
        }

        return ProfileItemModel(
            badge = badge,
            nickname = name,
            description = description,
            actionType = actionType,
            avatarUrl = avatarUrl
        )
    }
}
