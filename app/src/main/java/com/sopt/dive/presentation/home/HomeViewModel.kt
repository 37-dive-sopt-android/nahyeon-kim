package com.sopt.dive.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.data.RepositoryProvider
import com.sopt.dive.core.data.repository.UserRepository
import com.sopt.dive.core.util.UiState
import com.sopt.dive.presentation.home.model.ProfileActionType
import com.sopt.dive.presentation.home.model.ProfileBadge
import com.sopt.dive.presentation.home.model.ProfileDescription
import com.sopt.dive.presentation.home.model.ProfileItemModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository = RepositoryProvider.userRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<HomeUiState>>(UiState.Empty)
    val uiState: StateFlow<UiState<HomeUiState>> = _uiState.asStateFlow()

    fun loadUserInfo(userId: Long) {

        viewModelScope.launch {
            userRepository.getUser(userId)
                .onSuccess { memberModel ->
                    _uiState.update {
                        UiState.Success(
                            HomeUiState(
                                name = memberModel.name,
                                profileItems = getFakeProfileItems()
                            )
                        )
                    }
                }
                .onFailure {
                    _uiState.update { UiState.Failure }
                }
        }
    }

    private fun getFakeProfileItems(): List<ProfileItemModel> {
        return listOf(
            ProfileItemModel(
                badge = ProfileBadge.BIRTHDAY,
                nickname = "김나현",
                description = ProfileDescription.Exists("오늘 생일이에요! 🎉"),
                actionType = ProfileActionType.Music("Super Shy - NewJeans")
            ),
            ProfileItemModel(
                badge = ProfileBadge.MEMORIAL,
                nickname = "이서준",
                description = ProfileDescription.Exists("항상 그리워요."),
                actionType = ProfileActionType.None
            ),
            ProfileItemModel(
                badge = ProfileBadge.NONE,
                nickname = "최지우",
                description = ProfileDescription.None,
                actionType = ProfileActionType.Gift
            ),
            ProfileItemModel(
                badge = ProfileBadge.NONE,
                nickname = "박지민",
                description = ProfileDescription.Exists("요즘엔 산책이 좋아요"),
                actionType = ProfileActionType.Music("Love Lee - AKMU")
            ),
            ProfileItemModel(
                badge = ProfileBadge.BIRTHDAY,
                nickname = "정하린",
                description = ProfileDescription.Exists("오늘은 저를 위한 하루! 💖"),
                actionType = ProfileActionType.Gift
            ),
            ProfileItemModel(
                badge = ProfileBadge.MEMORIAL,
                nickname = "윤서연",
                description = ProfileDescription.Exists("늘 마음속에 함께해요."),
                actionType = ProfileActionType.None
            ),
            ProfileItemModel(
                badge = ProfileBadge.NONE,
                nickname = "한지후",
                description = ProfileDescription.Exists("요즘 커피에 빠졌어요 ☕"),
                actionType = ProfileActionType.Music("Coffee - BTS")
            ),
            ProfileItemModel(
                badge = ProfileBadge.NONE,
                nickname = "오은서",
                description = ProfileDescription.Exists("오늘은 하늘이 정말 예뻐요 🌤️"),
                actionType = ProfileActionType.None
            ),
            ProfileItemModel(
                badge = ProfileBadge.BIRTHDAY,
                nickname = "김도윤",
                description = ProfileDescription.Exists("축하해주셔서 감사해요! 🎂"),
                actionType = ProfileActionType.Music("Happy - Pharrell Williams")
            )
        )
    }
}
