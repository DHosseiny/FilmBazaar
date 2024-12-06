package com.example.filmbazzar.ui.upcoming


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.filmbazzar.ui.ANIMATION_DURATION
import com.example.filmbazzar.ui.theme.filmBazzarColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun UpcomingMoviesScreen(viewModel: UpcomingMoviesViewModel = hiltViewModel()) {

    val upcomingMoviePagingItems = viewModel.upcomingMovies.collectAsLazyPagingItems()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var endedAnimation by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(MaterialTheme.filmBazzarColors.backgroundColor)
            .fillMaxSize()
    ) {
        if (upcomingMoviePagingItems.loadState.refresh is LoadState.Error && upcomingMoviePagingItems.itemCount == 0) {
            RefreshErrorScreen(onRefreshErrorClick = { upcomingMoviePagingItems.refresh() })
        }

        if (upcomingMoviePagingItems.loadState.refresh is LoadState.NotLoading && !endedAnimation) {
            LaunchedEffect(Unit) {
                delay(ANIMATION_DURATION.toLong())
                endedAnimation = true
            }
        }

        if (upcomingMoviePagingItems.loadState.refresh is LoadState.NotLoading) {
            MovieListScreen(upcomingMoviePagingItems, endedAnimation) { movieTitle ->
                coroutineScope.launch {
                    movieTitle?.let {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        snackbarHostState.showSnackbar(movieTitle)
                    }
                }
            }
        }

        if ((upcomingMoviePagingItems.loadState.refresh is LoadState.Loading || !endedAnimation) && upcomingMoviePagingItems.loadState.refresh !is LoadState.Error) {
            LoadingScreen(
                upcomingMoviePagingItems.loadState.refresh is LoadState.Loading,
                upcomingMoviePagingItems.loadState.refresh is LoadState.NotLoading
            )
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}



