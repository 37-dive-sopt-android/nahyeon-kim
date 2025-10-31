package com.sopt.dive.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.presentation.home.model.ProfileBadge

@Composable
fun ProfileImage(
    badge: ProfileBadge,
    modifier: Modifier = Modifier,
) {
    val badgeIcon = remember {
        when (badge) {
            ProfileBadge.BIRTHDAY -> R.drawable.ic_profile_birthday
            ProfileBadge.MEMORIAL -> R.drawable.ic_profile_memorial
            ProfileBadge.NONE -> null
        }
    }

    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .size(52.dp)
                .clip(shape = CircleShape)
                .border(
                    shape = CircleShape,
                    color = Color.LightGray,
                    width = 1.dp
                )
        )
        badgeIcon?.let {
            Icon(
                imageVector = ImageVector.vectorResource(id = it),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.BottomEnd)

            )
        }
    }
}