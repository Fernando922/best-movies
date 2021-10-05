package br.com.dipaulamobilesolutions.bestmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import br.com.dipaulamobilesolutions.bestmovies.ui.main.view.MoviesList

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        redirectToHome()
    }

    private fun redirectToHome() {

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MoviesList::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }

}