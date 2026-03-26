package com.example.moviewithobjects.entities

class Movie(
    var title: String,
    var director: String,
    var genre: String
) {
    fun showMovie(): String {
        return "$title directed by $director - Genre: $genre"
    }
}