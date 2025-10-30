package com.sopt.dive.presentation.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.core.data.UserInfo
import com.sopt.dive.core.data.UserPreferences
import com.sopt.dive.core.designsystem.component.SoptBasicButton
import com.sopt.dive.core.designsystem.component.item.InfoItem
import com.sopt.dive.core.designsystem.theme.DiveTheme

@Composable
fun MyPageRoute(
    paddingValues: PaddingValues,
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    val userPrefs = UserPreferences(context)
    val userInfo = userPrefs.getUserInfo()

    MyPageScreen(
        userInfo = userInfo,
        onLogout = {
            userPrefs.signOut()
            onLogout()
        },
        modifier = Modifier
            .padding(paddingValues)
    )
}

@Composable
fun MyPageScreen(
    userInfo: UserInfo,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA))
            .padding(16.dp)
            .padding(top = 34.dp)
            .systemBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null
        )

        Text("김나현")

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoItem(
                label = "ID",
                value = userInfo.id
            )

            InfoItem(
                label = "Password",
                value = userInfo.password
            )

            InfoItem(
                label = "Nickname",
                value = userInfo.nickname
            )

            InfoItem(
                label = "MBTI",
                value = userInfo.mbti
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        SoptBasicButton(
            title = "로그아웃",
            onClick = onLogout
        )
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    DiveTheme {
        MyPageScreen(
            userInfo = UserInfo(
                id = "testUser",
                password = "1234",
                nickname = "테스트",
                mbti = "ISTJ"
            ),
            onLogout = {}
        )
    }
}
