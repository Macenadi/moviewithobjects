package com.example.moviewithobjects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviewithobjects.ui.theme.MovieWithObjectsTheme

class DetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val movieTitle = intent.getStringExtra("movieTitle") ?: "Unknown Movie"

        setContent {
            MovieWithObjectsTheme {
                DetailsScreen(
                    movieTitle = movieTitle,
                    onBackClick = { finish() }
                )
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun DetailsScreen(movieTitle: String, onBackClick: () -> Unit) {
    val description = when (movieTitle) {
        "Inception" -> "Inception is a science fiction movie directed by Christopher Nolan. It tells the story of a thief who enters people's dreams to steal secrets."
        "Titanic" -> "Titanic is a romantic drama directed by James Cameron. The film tells the story of Jack and Rose aboard the famous ship Titanic."
        else -> "No description available for this movie."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = movieTitle,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.size(24.dp))

        Button(onClick = onBackClick) {
            Text("Back")
        }
    }
}