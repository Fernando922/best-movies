package br.com.dipaulamobilesolutions.bestmovies.data.model


import com.google.gson.annotations.SerializedName

data class TopRatedMovie(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("genre_ids")
    val genre_ids: List<Int> = arrayListOf(),
    @SerializedName("poster_path")
    val posterPath: String = ""
)