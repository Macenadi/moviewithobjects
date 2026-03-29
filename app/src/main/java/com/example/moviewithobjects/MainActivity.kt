package com.example.moviewithobjects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviewithobjects.entities.Movie
import com.example.moviewithobjects.ui.theme.MovieWithObjectsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MovieWithObjectsTheme {
                val movieList = remember { mutableStateListOf<Movie>() }

                if (movieList.isEmpty()) {
                    movieList.add(Movie("Inception", "Christopher Nolan", "Sci-Fi"))
                    movieList.add(Movie("Titanic", "James Cameron", "Romance"))
                    movieList.add(Movie("The Dark Knight", "Christopher Nolan", "Action"))
                    movieList.add(Movie("Avatar", "James Cameron", "Fantasy"))
                    movieList.add(Movie("Interstellar", "Christopher Nolan", "Sci-Fi"))
                }

                DisplayMovieScreen(movieList)
            }
        }
    }
}

@Composable
fun DisplayMovieScreen(movies: SnapshotStateList<Movie>) {
    var title by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.size(50.dp))
            Text("Movie List")
            Spacer(modifier = Modifier.size(16.dp))
        }

        items(movies) { movie ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    text = movie.title + " by " + movie.director + " - " + movie.genre,
                    modifier = Modifier.weight(1f),
                    maxLines = 2,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )

                Button(
                    onClick = {
                        movies.remove(movie)
                    }
                ) {
                    Text("Delete")
                }
            }
            HorizontalDivider()
        }

        item {
            Spacer(modifier = Modifier.size(20.dp))
            Text("Add New Movie")
            Spacer(modifier = Modifier.size(12.dp))

            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = director,
                    onValueChange = { director = it },
                    label = { Text("Director") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = genre,
                    onValueChange = { genre = it },
                    label = { Text("Genre") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                Button(onClick = {
                    if (title.isNotBlank() && director.isNotBlank() && genre.isNotBlank()) {
                        movies.add(Movie(title, director, genre))
                        title = ""
                        director = ""
                        genre = ""
                    }
                }) {
                    Text("Add Movie")
                }
            }
        }
    }
}