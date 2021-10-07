package br.com.dipaulamobilesolutions.bestmovies.ui.moviesList.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dipaulamobilesolutions.bestmovies.data.model.Genre
import br.com.dipaulamobilesolutions.bestmovies.data.model.TopRatedMovie
import br.com.dipaulamobilesolutions.bestmovies.data.repository.MainRepository
import br.com.dipaulamobilesolutions.bestmovies.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesListViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val topRatedMovies = MutableLiveData<Resource<List<TopRatedMovie>>>()
    private val genres = MutableLiveData<Resource<List<Genre>>>()
    private var page = 1;
    private val compositeDisposable = CompositeDisposable()


    init {
        fetchGenres()
        fetchTopRatedMovies()
    }

    private fun fetchGenres() {
        topRatedMovies.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    genres.postValue(Resource.success(response.genres))
                }, { _ ->
                    genres.postValue(Resource.error("Algo deu errado", null))
                })
        )
    }

    private fun fetchTopRatedMovies() {
        topRatedMovies.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getTopRatedMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    topRatedMovies.postValue(Resource.success(response.results))
                }, { _ ->
                    topRatedMovies.postValue(Resource.error("Algo deu errado", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


    fun getTopRatedMovies(): LiveData<Resource<List<TopRatedMovie>>>{
        return topRatedMovies
    }

    fun getGenres() : LiveData<Resource<List<Genre>>>{
        return genres
    }

    fun callNextPage(){
        page  += 1
        fetchTopRatedMovies()
    }






}
