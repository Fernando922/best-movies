package br.com.dipaulamobilesolutions.bestmovies.data.model

import com.google.gson.annotations.SerializedName

data class TopRatedMoviesResponse(
    @SerializedName("page")
    val id: Int = 0,
    @SerializedName("results")
    val results: List<TopRatedMovie> = arrayListOf(),
)