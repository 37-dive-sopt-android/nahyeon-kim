package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.core.component.item.InfoItem
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        id = intent.getStringExtra("id") ?: "",
                        password = intent.getStringExtra("password") ?: "",
                        nickname = intent.getStringExtra("nickname") ?: "",
                        mbti = intent.getStringExtra("mbti") ?: "",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    id: String,
    password: String,
    nickname: String,
    mbti: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA))
            .padding(16.dp)
            .padding(top = 34.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null
        )

        Text("김나현")

        InfoItem(
            label = "ID",
            value = id
        )
        InfoItem(
            label = "Password",
            value = password
        )
        InfoItem(
            label = "Nickname",
            value = nickname
        )
        InfoItem(
            label = "MBTI",
            value = mbti
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    DiveTheme {
        MainScreen(
            id = "testUser",
            password = "1234",
            nickname = "테스트",
            mbti = "ISTJ"
        )
    }
}