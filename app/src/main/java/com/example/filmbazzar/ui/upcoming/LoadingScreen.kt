package com.example.filmbazzar.ui.upcoming

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.filmbazzar.R
import com.example.filmbazzar.ui.ANIMATION_DURATION
import com.example.filmbazzar.ui.BIG_LOGO_SIZE
import com.example.filmbazzar.ui.SMALL_LOGO_END_PADDING
import com.example.filmbazzar.ui.SMALL_LOGO_SIZE
import com.example.filmbazzar.ui.theme.filmBazzarColors


@Composable
fun LoadingScreen(isLoading: Boolean, isNotLoading: Boolean) {
    val configuration = LocalConfiguration.current
    val targetX =
        (configuration.screenWidthDp / 2) + (BIG_LOGO_SIZE.first / 2) - (SMALL_LOGO_END_PADDING * 2) - SMALL_LOGO_SIZE.first - 1
    val targetY =
        (configuration.screenHeightDp / 2) - (BIG_LOGO_SIZE.second / 2) + (SMALL_LOGO_SIZE.second / 2) - 4.5
    val targetOffset = Offset(targetX.toFloat(), -targetY.toFloat())
    val offsetAnimation by animateOffsetAsState(
        targetValue = if (isNotLoading) targetOffset else Offset.Zero,
        animationSpec = tween(durationMillis = ANIMATION_DURATION)
    )

    val hAnimation by animateDpAsState(
        targetValue = if (isNotLoading) SMALL_LOGO_SIZE.first.dp else BIG_LOGO_SIZE.first.dp,
        animationSpec = tween(durationMillis = ANIMATION_DURATION)
    )

    val wAnimation by animateDpAsState(
        targetValue = if (isNotLoading) SMALL_LOGO_SIZE.second.dp else BIG_LOGO_SIZE.second.dp,
        animationSpec = tween(durationMillis = ANIMATION_DURATION)
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .size(hAnimation, wAnimation)
                .align(Alignment.Center)
                .offset(offsetAnimation.x.dp, offsetAnimation.y.dp),
            painter = painterResource(id = R.drawable.bazzar_logo),
            contentDescription = stringResource(R.string.bazzar_logo)
        )
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 160.dp)
                    .size(32.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.filmBazzarColors.progressBarColor
            )
        }
    }
}

@Composable
@Preview
fun LoadingScreenPreview() {
    LoadingScreen(isLoading = true, isNotLoading = false)
}
