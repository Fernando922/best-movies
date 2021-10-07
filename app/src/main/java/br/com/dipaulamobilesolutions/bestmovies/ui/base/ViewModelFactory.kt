package br.com.dipaulamobilesolutions.bestmovies.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.dipaulamobilesolutions.bestmovies.data.api.ApiHelper
import br.com.dipaulamobilesolutions.bestmovies.data.repository.MainRepository
import br.com.dipaulamobilesolutions.bestmovies.data.repository.MovieInfoRepository
import br.com.dipaulamobilesolutions.bestmovies.ui.moviesList.viewmodel.MoviesListViewModel
import br.com.dipaulamobilesolutions.bestmovies.ui.movieInfo.viewmodel.MovieInfoViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper, private val movieId: String?) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesListViewModel::class.java)) {
            return MoviesListViewModel(MainRepository(apiHelper)) as T
        }
        if (modelClass.isAssignableFrom(MovieInfoViewModel::class.java)) {
            return MovieInfoViewModel(MovieInfoRepository(apiHelper), movieId) as T
        }
        throw IllegalArgumentException("Classe desconhecida")
    }

}