package br.com.dipaulamobilesolutions.bestmovies.data.api

import br.com.dipaulamobilesolutions.bestmovies.data.model.*
import io.reactivex.Single

interface ApiService {
    fun getGenres(): Single<GenreResponse>

    fun getTopRatedMovies(page: Int): Single<TopRatedMoviesResponse>

    fun getMovieDetail(id: String): Single<MovieDetail>
}