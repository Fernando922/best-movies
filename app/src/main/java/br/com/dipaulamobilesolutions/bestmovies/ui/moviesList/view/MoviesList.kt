package br.com.dipaulamobilesolutions.bestmovies.ui.moviesList.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.dipaulamobilesolutions.bestmovies.R
import br.com.dipaulamobilesolutions.bestmovies.data.api.ApiHelper
import br.com.dipaulamobilesolutions.bestmovies.data.api.ApiServiceImpl
import br.com.dipaulamobilesolutions.bestmovies.data.model.Genre
import br.com.dipaulamobilesolutions.bestmovies.data.model.TopRatedMovie
import br.com.dipaulamobilesolutions.bestmovies.ui.base.ViewModelFactory
import br.com.dipaulamobilesolutions.bestmovies.ui.moviesList.adapter.MoviesListAdapter
import br.com.dipaulamobilesolutions.bestmovies.ui.moviesList.viewmodel.MoviesListViewModel
import br.com.dipaulamobilesolutions.bestmovies.utils.Status
import kotlinx.android.synthetic.main.activity_movies_list.*


@Suppress("NON_EXHAUSTIVE_WHEN")
class MoviesList : AppCompatActivity() {

    private lateinit var mainViewModel: MoviesListViewModel
    private lateinit var adapter: MoviesListAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBarTitle()
        setContentView(R.layout.activity_movies_list)
        setupViewModel()
        setupObserver()
    }

    private fun setActionBarTitle() {
        supportActionBar?.title = "Top Rated Movies"
    }

    private fun setupUI(genres: List<Genre>) {
        moviesList.layoutManager = LinearLayoutManager(this)
        adapter = MoviesListAdapter(arrayListOf(), genres)
        moviesList.addItemDecoration(
            DividerItemDecoration(
                moviesList.context,
                (moviesList.layoutManager as LinearLayoutManager).orientation
            )
        )
        moviesList.adapter = adapter
        moviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mainViewModel.callNextPage()
                }
            }
        })
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()), null)
        ).get(MoviesListViewModel::class.java)
    }


    private fun setupObserver() {

        mainViewModel.getGenres().observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    loadingIndicator.visibility = View.GONE
                    it.data?.let { genres -> setGenres(genres) }
                    moviesList.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    loadingIndicator.visibility = View.VISIBLE
                    moviesList.visibility = View.GONE
                }
                Status.ERROR -> {
                    loadingIndicator.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        mainViewModel.getTopRatedMovies().observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    loadingIndicator.visibility = View.GONE
                    it.data?.let { topRatedMovies -> renderList(topRatedMovies) }
                    moviesList.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    loadingIndicator.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })


    }

    private fun setGenres(genres: List<Genre>) {
        setupUI(genres)
    }


    private fun renderList(topRatedMovieList: List<TopRatedMovie>) {
        adapter.addData(topRatedMovieList)
        adapter.notifyDataSetChanged()
    }





}