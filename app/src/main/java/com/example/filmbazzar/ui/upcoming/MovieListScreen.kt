package com.example.filmbazzar.ui.upcoming

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.filmbazzar.R
import com.example.filmbazzar.domain.model.UpcomingMovie
import com.example.filmbazzar.ui.SMALL_LOGO_END_PADDING
import com.example.filmbazzar.ui.SMALL_LOGO_SIZE
import com.example.filmbazzar.ui.component.MovieImage
import com.example.filmbazzar.ui.theme.filmBazzarColors

@Composable
fun MovieListScreen(
    upcomingMoviePagingItems: LazyPagingItems<UpcomingMovie>,
    endedAnimation: Boolean,
    onClickMovie: (movieTitle: String?) -> Unit
) {
    Column {
        MovieListTopBar(endedAnimation)
        MovieList(upcomingMoviePagingItems, onClickMovie = onClickMovie)
    }
}

@Composable
fun MovieListTopBar(endAnimation: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            color = MaterialTheme.filmBazzarColors.topAppBarTitleColor,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            text = stringResource(R.string.discover)
        )
        if (endAnimation) {
            Image(
                modifier = Modifier
                    .padding(end = SMALL_LOGO_END_PADDING.dp)
                    .size(SMALL_LOGO_SIZE.first.dp, SMALL_LOGO_SIZE.second.dp)
                    .align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.bazzar_logo),
                contentDescription = stringResource(R.string.bazzar_logo)
            )
        }
    }
}

@Composable
fun MovieList(
    upcomingMoviePagingItems: LazyPagingItems<UpcomingMovie>,
    onClickMovie: (movieTitle: String?) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 119.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = upcomingMoviePagingItems.itemCount, key = { index ->
            upcomingMoviePagingItems[index]?.sortId ?: 0
        }) { index ->
            val upcomingMovie = upcomingMoviePagingItems[index] ?: return@items
            UpcomingMovieHolder(upcomingMovie = upcomingMovie, onClickMovie = onClickMovie)
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            FooterHolder(
                modifier = Modifier
                    .fillMaxWidth(),
                upcomingMoviePagingItems.loadState.append,
                tyrAgainClick = { upcomingMoviePagingItems.retry() })
        }
    }
}

@Composable
fun FooterHolder(modifier: Modifier = Modifier, appendState: LoadState, tyrAgainClick: () -> Unit) {
    Row(
        modifier = modifier.padding(bottom = 22.dp, end = 22.dp, start = 22.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (appendState is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterVertically),
                color = MaterialTheme.filmBazzarColors.progressBarColor
            )
        } else if (appendState is LoadState.Error) {
            val error = appendState.error
            Text(
                modifier = Modifier.weight(10f),
                text = error.message ?: stringResource(R.string.default_error),
                color = MaterialTheme.filmBazzarColors.primaryTitleColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                modifier = Modifier.height(height = 36.dp),
                onClick = tyrAgainClick,
                shape = RoundedCornerShape(corner = CornerSize(4.dp)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.filmBazzarColors.tryAgainTitleColor,
                    containerColor = MaterialTheme.filmBazzarColors.tryAgainButtonColor,
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.filmBazzarColors.tryAgainButtonBorderColor
                )
            ) {
                Text(text = stringResource(R.string.try_again), style = TextStyle(fontSize = 14.sp))
            }
        }
    }
}

@Composable
fun UpcomingMovieHolder(upcomingMovie: UpcomingMovie, onClickMovie: (movieTitle: String?) -> Unit) {
    Column(
        modifier = Modifier.clickable { onClickMovie(upcomingMovie.title) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieImage(
            imageUrl = upcomingMovie.posterPath!!,
            contentDescription = stringResource(
                R.string.poster_description,
                upcomingMovie.posterPath
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        upcomingMovie.title?.let {
            Text(
                text = upcomingMovie.title,
                color = MaterialTheme.filmBazzarColors.primaryTitleColor,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun MovieListTopBarPreview() {
    MovieListTopBar(true)
}

@Composable
@Preview(showBackground = true)
fun FooterHolderPreview() {
    FooterHolder(appendState = LoadState.Error(Throwable("sdfsdfsdfsdfsasdasdasdsadsadasdaddfsdfsdf"))) {}
}