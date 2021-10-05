package br.com.dipaulamobilesolutions.bestmovies.data.model


import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("runtime")
    val runtime: Int = 0,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("overview")
    val overview: String = "",
)