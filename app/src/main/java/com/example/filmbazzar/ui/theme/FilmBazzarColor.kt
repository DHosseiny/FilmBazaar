package com.example.filmbazzar.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
class FilmBazzarColors(
    val progressBarColor: Color = Color.Unspecified,
    val backgroundColor: Color = Color.Unspecified,
    val primaryTitleColor: Color = Color.Unspecified,
    val topAppBarTitleColor: Color = Color.Unspecified,
    val tryAgainTitleColor: Color = Color.Unspecified,
    val tryAgainButtonColor: Color = Color.Unspecified,
    val tryAgainButtonBorderColor: Color = Color.Unspecified,
    val refreshErrorTitleColor: Color = Color.Unspecified,
    val refreshErrorButtonColor: Color = Color.Unspecified,
)

val LightBackgroundColor = Color(color = 0xFFFFFFFF)
val LightPrimaryTitleColor = Color(color = 0xFF000000)
val LightTryAgainButtonColor = Color(color = 0xFFCBC8C8)


val DarkBackgroundColor = Color(color = 0xFF131313)
val DarkPrimaryTitleColor = Color(color = 0xFFFFFFFF)
val DarkTryAgainButtonColor = Color(color = 0xFF191A1F)


val ProgressBarColor = Color(color = 0xFF4BC381)
val TopAppBarTitleColor = Color(color = 0xFFFF3D00)
val TryAgainTitleColor = Color(color = 0xFFEB5757)
val TryAgainButtonBorderColor = Color(color = 0xFF44464E)
val RefreshErrorTitleColor = Color(color = 0xFF7E91B7)
val RefreshErrorButtonColor = Color(color = 0xFF1C3051)


val LightFilmBazzarColors = FilmBazzarColors(
    progressBarColor = ProgressBarColor,
    backgroundColor = LightBackgroundColor,
    primaryTitleColor = LightPrimaryTitleColor,
    topAppBarTitleColor = TopAppBarTitleColor,
    tryAgainTitleColor = TryAgainTitleColor,
    tryAgainButtonColor = LightTryAgainButtonColor,
    tryAgainButtonBorderColor = TryAgainButtonBorderColor,
    refreshErrorTitleColor = RefreshErrorTitleColor,
    refreshErrorButtonColor = RefreshErrorButtonColor
)

val DarkFilmBazzarColors = FilmBazzarColors(
    progressBarColor = ProgressBarColor,
    backgroundColor = DarkBackgroundColor,
    primaryTitleColor = DarkPrimaryTitleColor,
    topAppBarTitleColor = TopAppBarTitleColor,
    tryAgainTitleColor = TryAgainTitleColor,
    tryAgainButtonColor = DarkTryAgainButtonColor,
    tryAgainButtonBorderColor = TryAgainButtonBorderColor,
    refreshErrorTitleColor = RefreshErrorTitleColor,
    refreshErrorButtonColor = RefreshErrorButtonColor
)

val LocalFilmBazzarColors = staticCompositionLocalOf { FilmBazzarColors() }

val MaterialTheme.filmBazzarColors: FilmBazzarColors
    @Composable
    @ReadOnlyComposable
    get() = LocalFilmBazzarColors.current