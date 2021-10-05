package br.com.dipaulamobilesolutions.bestmovies.ui.main.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dipaulamobilesolutions.bestmovies.data.model.TopRatedMovie
import br.com.dipaulamobilesolutions.bestmovies.data.repository.MainRepository
import br.com.dipaulamobilesolutions.bestmovies.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val topRatedMovies = MutableLiveData<Resource<List<TopRatedMovie>>>()
    private val compositeDisposable = CompositeDisposable()


    init {
        fetchTopRatedMovies()
    }

    private fun fetchTopRatedMovies() {
        topRatedMovies.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getTopRatedMovies()
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






}
