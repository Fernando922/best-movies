package br.com.dipaulamobilesolutions.bestmovies.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getTopRatedMovies() = apiService.getTopRatedMovies()

    fun getGenres() = apiService.getGenres()

    fun getMovieDetail(id: String) = apiService.getMovieDetail(id)
}
