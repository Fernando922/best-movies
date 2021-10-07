package br.com.dipaulamobilesolutions.bestmovies.data.repository

import br.com.dipaulamobilesolutions.bestmovies.data.api.ApiHelper
import br.com.dipaulamobilesolutions.bestmovies.data.model.*
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getGenres(): Single<GenreResponse>{
        return apiHelper.getGenres()
    }

    fun getTopRatedMovies(page: Int): Single<TopRatedMoviesResponse>{
        return apiHelper.getTopRatedMovies(page)
    }

}