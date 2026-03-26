package com.example.moviewithobjects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
                    val a = Movie("Inception", "Christopher Nolan", "Sci-Fi")
                    val b = Movie("Titanic", "James Cameron", "Romance")
                    val c = Movie("The Dark Knight", "Christopher Nolan", "Action")
                    val d = Movie("Avatar", "James Cameron", "Fantasy")
                    val e = Movie("Interstellar", "Christopher Nolan", "Sci-Fi")

                    movieList.add(a)
                    movieList.add(b)
                    movieList.add(c)
                    movieList.add(d)
                    movieList.add(e)
                }

                DisplayMovieList(movieList)
            }
        }
    }
}

@Composable
fun DisplayMovieList(movies: SnapshotStateList<Movie>) {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.size(75.dp))
            Text("Movie List")
        }

        items(movies) { movie ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(movie.title + " by " + movie.director)

                Button(onClick = {
                    movies.remove(movie)
                }) {
                    Text("Delete")
                }
            }
            HorizontalDivider()
        }
    }
}