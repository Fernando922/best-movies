package br.com.dipaulamobilesolutions.bestmovies.data.repository

import br.com.dipaulamobilesolutions.bestmovies.data.api.ApiHelper
import br.com.dipaulamobilesolutions.bestmovies.data.model.Genre
import br.com.dipaulamobilesolutions.bestmovies.data.model.MovieDetail
import br.com.dipaulamobilesolutions.bestmovies.data.model.TopRatedMovie
import br.com.dipaulamobilesolutions.bestmovies.data.model.TopRatedMoviesResponse
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getGenres(): Single<List<Genre>>{
        return apiHelper.getGenres()
    }

    fun getTopRatedMovies(): Single<TopRatedMoviesResponse>{
        return apiHelper.getTopRatedMovies()
    }

}