package br.com.dipaulamobilesolutions.bestmovies.ui.movieInfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dipaulamobilesolutions.bestmovies.data.model.MovieDetail
import br.com.dipaulamobilesolutions.bestmovies.data.repository.MovieInfoRepository
import br.com.dipaulamobilesolutions.bestmovies.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieInfoViewModel(private val movieInfoRepository: MovieInfoRepository, movieId: String?) : ViewModel() {

    private val movieDetail = MutableLiveData<Resource<MovieDetail>>()
    private val compositeDisposable = CompositeDisposable()


    init {
        if(movieId != null){
            fetchTopRatedMovies(movieId)
        }
    }

    private fun fetchTopRatedMovies(movieId: String) {
        movieDetail.postValue(Resource.loading(null))
        compositeDisposable.add(
            movieInfoRepository.getMovieDetail(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    movieDetail.postValue(Resource.success(response))
                }, { _ ->
                    movieDetail.postValue(Resource.error("Algo deu errado", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


    fun getMovieDetail(): LiveData<Resource<MovieDetail>>{
        return movieDetail
    }






}
