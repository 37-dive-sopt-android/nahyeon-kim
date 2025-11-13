package com.sopt.dive.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.presentation.search.component.FlipCard
import com.sopt.dive.presentation.search.component.SpringCard

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
    val cards = listOf("flip", "spring")

    LazyRow(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA)),
        contentPadding = PaddingValues(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(cards) { type ->
            when (type) {
                "flip" -> FlipCard()
                "spring" -> SpringCard()
            }
        }
    }
}
@Preview
@Composable
private fun SearchScreenPreview() {
    DiveTheme {
        SearchScreen()
    }
}
