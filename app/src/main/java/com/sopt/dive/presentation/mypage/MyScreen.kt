package com.sopt.dive.presentation.mypage

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.dive.R
import com.sopt.dive.core.designsystem.component.SoptBasicButton
import com.sopt.dive.core.designsystem.component.item.InfoItem
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.util.UiState
import com.sopt.dive.core.util.collectSideEffect

@Composable
fun MyPageRoute(
    paddingValues: PaddingValues,
    onLogout: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    viewModel.sideEffect.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is MyPageSideEffect.ShowToast -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }

            is MyPageSideEffect.NavigateToSignIn -> {
                onLogout()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadUserInfo()
    }

    when (uiState) {
        is UiState.Success -> {
            val data = (uiState as UiState.Success<MyPageUiState>).data
            MyPageScreen(
                username = data.username,
                name = data.name,
                email = data.email,
                age = data.age,
                onLogout = viewModel::signOut,
                modifier = Modifier.padding(paddingValues)
            )
        }

        is UiState.Failure -> {}

        else -> {}
    }
}

@Composable
fun MyPageScreen(
    username: String,
    name: String,
    email: String,
    age: Int,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA))
            .padding(16.dp)
            .padding(top = 34.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null
        )

        Text(name)

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoItem(
                label = "Username",
                value = username
            )

            InfoItem(
                label = "Name",
                value = name
            )

            InfoItem(
                label = "Email",
                value = email
            )

            InfoItem(
                label = "Age",
                value = age.toString()
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
            username = "testUser",
            name = "김나현",
            email = "test@example.com",
            age = 25,
            onLogout = {}
        )
    }
}