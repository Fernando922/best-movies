package br.com.dipaulamobilesolutions.bestmovies.data.api

import br.com.dipaulamobilesolutions.bestmovies.data.model.GenreResponse
import br.com.dipaulamobilesolutions.bestmovies.data.model.MovieDetail
import br.com.dipaulamobilesolutions.bestmovies.data.model.TopRatedMoviesResponse
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {
    override fun getGenres(): Single<GenreResponse> {
        return Rx2AndroidNetworking.get("https://api.themoviedb.org/3/genre/movie/list?language=pt-BR")
            .addHeaders("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZjIzNTcyYTMyYWY5MmYxNDY1MzVhM2FkNTlkNDA0MCIsInN1YiI6IjYxNTY0NTExMDdlMjgxMDAyZGE3YzVlMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xp2JISfEFIcYQTGG8pKeJJb6SIxCzLd4LIsAa2mo_3U")
            .build()
            .getObjectSingle(GenreResponse::class.java)
    }

    override fun getTopRatedMovies(page: Int): Single<TopRatedMoviesResponse> {
        return Rx2AndroidNetworking.get("https://api.themoviedb.org/3/movie/top_rated?page=${page}&language=pt-BR")
            .addHeaders("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZjIzNTcyYTMyYWY5MmYxNDY1MzVhM2FkNTlkNDA0MCIsInN1YiI6IjYxNTY0NTExMDdlMjgxMDAyZGE3YzVlMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xp2JISfEFIcYQTGG8pKeJJb6SIxCzLd4LIsAa2mo_3U")
            .build()
            .getObjectSingle(TopRatedMoviesResponse::class.java)
    }

    override fun getMovieDetail(id: String): Single<MovieDetail> {
        return Rx2AndroidNetworking.get("https://api.themoviedb.org/3/movie/${id}?language=pt-BR")
            .addHeaders("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZjIzNTcyYTMyYWY5MmYxNDY1MzVhM2FkNTlkNDA0MCIsInN1YiI6IjYxNTY0NTExMDdlMjgxMDAyZGE3YzVlMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xp2JISfEFIcYQTGG8pKeJJb6SIxCzLd4LIsAa2mo_3U")
            .build()
            .getObjectSingle(MovieDetail::class.java)
    }
}