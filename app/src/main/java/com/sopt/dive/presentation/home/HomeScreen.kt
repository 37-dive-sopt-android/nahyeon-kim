package com.sopt.dive.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.core.data.UserInfo
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.extension.noRippleClickable
import com.sopt.dive.presentation.home.component.ProfileItem
import com.sopt.dive.presentation.home.model.ProfileItemModel

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    navigateToProfile: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        userInfo = uiState.userInfo,
        profileItems = uiState.profileItems,
        navigateToProfile = navigateToProfile,
        modifier = Modifier.padding(paddingValues)
    )
}


@Composable
private fun HomeScreen(
    userInfo: UserInfo,
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
                userInfo = userInfo,
                modifier = Modifier.padding(bottom = 16.dp),
                navigateToProfile = navigateToProfile
            )
        }

        items(
            items = profileItems,
            key = { it.nickname }
        ) { profile ->
            with(profile) {
                ProfileItem(
                    badge = badge,
                    nickname = nickname,
                    description = description,
                    actionType = actionType
                )
            }
        }
    }
}

@Composable
private fun MyProfileSection(
    userInfo: UserInfo,
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

        Text(text = "${userInfo.id} 님 😻")
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    DiveTheme {
        HomeScreen(
            userInfo = HomeUiState.Fake.userInfo,
            profileItems = HomeUiState.Fake.profileItems,
            navigateToProfile = {}
        )
    }
}
