package com.example.filmbazzar.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.filmbazzar.ui.theme.FilmBazzarTheme
import com.example.filmbazzar.ui.theme.filmBazzarColors
import com.example.filmbazzar.ui.upcoming.UpcomingMoviesScreen
import com.example.filmbazzar.ui.upcoming.UpcomingMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmBazzarTheme {
                UpcomingMoviesScreen()
            }
        }
    }
}

