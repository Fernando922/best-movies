package br.com.dipaulamobilesolutions.bestmovies.ui.movieInfo.view


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.dipaulamobilesolutions.bestmovies.R
import br.com.dipaulamobilesolutions.bestmovies.data.api.ApiHelper
import br.com.dipaulamobilesolutions.bestmovies.data.api.ApiServiceImpl
import br.com.dipaulamobilesolutions.bestmovies.data.model.MovieDetail
import br.com.dipaulamobilesolutions.bestmovies.ui.base.ViewModelFactory
import br.com.dipaulamobilesolutions.bestmovies.ui.movieInfo.viewmodel.MovieInfoViewModel
import br.com.dipaulamobilesolutions.bestmovies.utils.Status
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_info.*
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieInfo : AppCompatActivity() {

    private lateinit var movieInfoViewModel: MovieInfoViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)
        getMovieId()
    }

    private fun getMovieId() {
        val movieId = intent?.extras?.getString("id")
        if (movieId != null) {
            setupViewModel(movieId)
        }
        setupObserver()
    }

    private fun setActionBarTitle(movieTitle: String) {
        supportActionBar?.title = movieTitle
    }



    private fun setupViewModel(movieId: String) {
        movieInfoViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()), movieId)
        ).get(MovieInfoViewModel::class.java)
    }


    private fun setupObserver() {
        movieInfoViewModel.getMovieDetail().observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    loadView.visibility = View.GONE
                    it.data?.let { movieDetail -> updateUI(movieDetail) }
                }
                Status.ERROR -> {
                    loadingIndicator.visibility = View.GONE
                    errorMessage.visibility = View.VISIBLE
                }
            }
        })
    }


    private fun updateUI(movieDetail: MovieDetail) {
        updateScreenTitle(movieDetail.title)

        val date = LocalDate.parse(movieDetail.releaseDate)
        val formatter = DateTimeFormatter.ofPattern("yyyy")
        movieYear.text = date.format(formatter)

        Glide.with(movieImage.context)
            .load("https://image.tmdb.org/t/p/w500${movieDetail.posterPath}")
            .into(movieImage)

        movieTitle.text =  movieDetail.title


        val hours = movieDetail.runtime / 60
        val minutes = movieDetail.runtime % 60
        movieDuration.text = "${hours}h ${minutes}m"


        val voteAverage = movieDetail.voteAverage*10
        val formatAverage = DecimalFormat("0.#")
        movieRelevance.text = "${formatAverage.format(voteAverage)}% relevante"



        moviePhrase.text = movieDetail.tagline
        movieSynopsis.text = movieDetail.overview

    }

    private fun updateScreenTitle(movieTitle: String) {
        setActionBarTitle(movieTitle)
    }


}