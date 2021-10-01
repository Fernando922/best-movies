package br.com.dipaulamobilesolutions.bestmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MoviesList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setActionBarTitle()

    }

    private fun setActionBarTitle() {
        supportActionBar?.title = "Top Rated Movies"
    }
}