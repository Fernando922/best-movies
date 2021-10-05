package br.com.dipaulamobilesolutions.bestmovies.data.api

import br.com.dipaulamobilesolutions.bestmovies.data.model.Genre
import br.com.dipaulamobilesolutions.bestmovies.data.model.MovieDetail
import br.com.dipaulamobilesolutions.bestmovies.data.model.TopRatedMovie
import br.com.dipaulamobilesolutions.bestmovies.data.model.TopRatedMoviesResponse
import io.reactivex.Single

interface ApiService {
    fun getGenres(): Single<List<Genre>>

    fun getTopRatedMovies(): Single<TopRatedMoviesResponse>

    fun getMovieDetail(id: String): Single<MovieDetail>
}