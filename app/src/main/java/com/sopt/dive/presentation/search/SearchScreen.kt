package com.sopt.dive.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.presentation.search.component.FlipCard

@Composable
fun SearchRoute(
    paddingValues: PaddingValues
) {
    SearchScreen(
        modifier = Modifier.padding(paddingValues)
    )
}

@Composable
private fun SearchScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlipCard()
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    DiveTheme {
        SearchScreen()
    }
}
