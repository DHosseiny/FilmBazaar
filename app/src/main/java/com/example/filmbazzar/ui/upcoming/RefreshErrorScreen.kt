package com.example.filmbazzar.ui.upcoming

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmbazzar.R
import com.example.filmbazzar.ui.theme.filmBazzarColors

@Composable
fun RefreshErrorScreen(onRefreshErrorClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(79.dp, 88.dp),
            painter = painterResource(id = R.drawable.ic_refresh_error),
            contentDescription = stringResource(R.string.icon_refresh_error)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.connection_glitch),
            color = MaterialTheme.filmBazzarColors.primaryTitleColor,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, lineHeight = 24.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            text = stringResource(R.string.seems_like_there_s_an_internet_connection_problem),
            color = MaterialTheme.filmBazzarColors.refreshErrorTitleColor,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center
            )

        )
        Spacer(modifier = Modifier.height(30.dp))

        Button(
            modifier = Modifier.size(100.dp, 48.dp),
            onClick = onRefreshErrorClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.filmBazzarColors.primaryTitleColor,
                containerColor = MaterialTheme.filmBazzarColors.refreshErrorButtonColor
            )
        ) { Text(text = stringResource(R.string.retry)) }
    }
}

@Composable
@Preview
fun RefreshErrorScreenPreview() {
    RefreshErrorScreen {}
}