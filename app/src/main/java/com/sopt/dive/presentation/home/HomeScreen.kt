package com.sopt.dive.presentation.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.core.data.UserPreferences
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.extension.noRippleClickable
import com.sopt.dive.core.util.UiState
import com.sopt.dive.presentation.home.component.ProfileItem
import com.sopt.dive.presentation.home.model.ProfileItemModel

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    navigateToProfile: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val userPrefs = UserPreferences(context)
    val userId = userPrefs.getUserId()

    LaunchedEffect(userId) {
        viewModel.loadUserInfo(userId)
    }

    when (uiState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is UiState.Success -> {
            val data = (uiState as UiState.Success<HomeUiState>).data
            HomeScreen(
                name = data.name,
                profileItems = data.profileItems,
                navigateToProfile = navigateToProfile,
                modifier = Modifier.padding(paddingValues)
            )
        }
        is UiState.Failure -> {
            LaunchedEffect(Unit) {
                Toast.makeText(
                    context,
                    "사용자 정보를 불러오는데 실패했습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        else -> {}
    }
}

@Composable
private fun HomeScreen(
    name: String,
    profileItems: List<ProfileItemModel>,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA)),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            MyProfileSection(
                name = name,
                modifier = Modifier.padding(bottom = 16.dp),
                navigateToProfile = navigateToProfile
            )
        }

        items(
            items = profileItems,
            key = { it.nickname + it.avatarUrl }
        ) { profile ->
            with(profile) {
                ProfileItem(
                    badge = badge,
                    nickname = nickname,
                    description = description,
                    actionType = actionType,
                    avatarUrl = avatarUrl
                )
            }
        }
    }
}

@Composable
private fun MyProfileSection(
    name: String,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .noRippleClickable(onClick = navigateToProfile),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .clip(shape = CircleShape)
        )

        Text(text = "$name 님 😻")
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    DiveTheme {
        HomeScreen(
            name = "나현",
            profileItems = HomeUiState.Fake.profileItems,
            navigateToProfile = {}
        )
    }
}
