package com.sopt.dive.presentation.search.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.sopt.dive.R
import com.sopt.dive.core.extension.noRippleClickable

@Composable
fun SpringCard(
    modifier: Modifier = Modifier
) {
    var isFlipped by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isFlipped, label = "transition")

    val commonSpring = remember {
        spring<Float>(
            dampingRatio = 0.5f,
            stiffness = 177.8f
        )
    }

    val rotation by transition.animateFloat(
        transitionSpec = { commonSpring },
        label = "rotation"
    ) { flipped ->
        if (flipped) 180f else 0f
    }

    val textAlpha by transition.animateFloat(
        transitionSpec = {
            if (targetState) {
                tween(durationMillis = 400, delayMillis = 200)
            } else {
                tween(durationMillis = 200)
            }
        },
        label = "textAlpha"
    ) { flipped ->
        if (flipped) 1f else 0f
    }

    val offset by transition.animateFloat(
        transitionSpec = { commonSpring },
        label = "offset"
    ) { flipped ->
        if (flipped) 25f else 0f
    }


    Box(
        modifier = modifier
            .size(width = 300.dp, height = 400.dp)
            .noRippleClickable {
                isFlipped = !isFlipped
            }
    ) {
        FrontCard(
            rotation = rotation,
            offset = offset
        )

        BackCard(
            rotation = rotation,
            textAlpha = textAlpha
        )
    }
}

@Composable
private fun FrontCard(
    rotation: Float,
    offset: Float
) {
    val frontShape = RoundedCornerShape(
        topStart = 30.dp,
        topEnd = 70.dp,
        bottomStart = 180.dp,
        bottomEnd = 20.dp
    )

    Image(
        painter = painterResource(id = R.drawable.img_front_card),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .offset(x = offset.dp, y = offset.dp)
            .shadow(
                elevation = if (rotation < 20f) 16.dp else 0.dp,
                shape = frontShape
            )
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .clip(frontShape)
            .zIndex(if (rotation <= 90f) 1f else -1f)
    )
}

@Composable
private fun BackCard(
    rotation: Float,
    textAlpha: Float
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(
                RoundedCornerShape(
                    topStart = 70.dp,
                    topEnd = 30.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 180.dp
                )
            )
            .background(Color.White)
            .zIndex(0f),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = (rotation > 90f),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = "Still thinkin' about you, full of question marks Wanna figure this out for sure 매번 기분은 high You'd better hold me tight You know everything feel so right Feels like a dream 둘 원하게 돼 oh (uh – uh) Driving me up ooh 넌 중독적인 loop I never lie, there is no filter 같은 맘이길 right now Maybe it's love 나 빠진 듯해 미로 같아 like a crossword We can't explain yeah 이 감정은 thousand 단어론 yet We can't explain yeah You're chosen and chosen 너 빼곤 fade out 너와 날 이어 주는 hyphen, yeah 우리 둘만 아는 chapter의 한 page, oh We can't explain yeah 이 감정은 thousand 커지는 love yeah",
                fontSize = 25.sp,
                color = Color.Gray,
                modifier = Modifier.graphicsLayer {
                    alpha = textAlpha
                }
            )
        }
    }
}

@Preview
@Composable
private fun FlipCardPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C2C2C)),
        contentAlignment = Alignment.Center
    ) {
        SpringCard(
            modifier = Modifier.width(300.dp)
        )
    }
}
