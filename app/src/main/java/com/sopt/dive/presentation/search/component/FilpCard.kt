package com.sopt.dive.presentation.search.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.core.extension.noRippleClickable

private const val FLIP_DURATION = 600
private const val CAMERA_DISTANCE = 12f

@Composable
fun FlipCard(
    modifier: Modifier = Modifier
) {
    var isFlipped by remember { mutableStateOf(false) }

    val rotateCardY by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(FLIP_DURATION),
        label = "rotation"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .noRippleClickable { isFlipped = !isFlipped },
        contentAlignment = Alignment.Center
    ) {
        val isFrontVisible = rotateCardY <= 90f
        val imageRes = if (isFrontVisible) R.drawable.img_front_card else R.drawable.img_back_card

        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(width = 300.dp, height = 400.dp)
                .graphicsLayer {
                    rotationY = rotateCardY
                    cameraDistance = CAMERA_DISTANCE * density
                    if (!isFrontVisible) scaleX = -1f
                }
        )
    }
}