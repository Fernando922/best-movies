package br.com.dipaulamobilesolutions.bestmovies.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dipaulamobilesolutions.bestmovies.R
import br.com.dipaulamobilesolutions.bestmovies.data.model.TopRatedMovie
import br.com.dipaulamobilesolutions.bestmovies.ui.movieInfo.view.MovieInfo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainAdapter(
    private val topRatedMovies: ArrayList<TopRatedMovie>,
    private val context: Context
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(topRatedMovie: TopRatedMovie) {
            val date = LocalDate.parse(topRatedMovie.releaseDate)
            val formatter = DateTimeFormatter.ofPattern("yyyy")

            itemView.movieTitle.text = topRatedMovie.title
            itemView.movieYear.text = date.format(formatter)
            Glide.with(itemView.moviePoster.context)
                .load("https://image.tmdb.org/t/p/w500${topRatedMovie.posterPath}")
                .into(itemView.moviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.bind(topRatedMovies[position])

        val context = holder.itemView.context

        holder.itemView.setOnClickListener {
            val item = topRatedMovies[position]
            val intent = Intent(context, MovieInfo::class.java)
            intent.putExtra("id", item.id.toString() )
            intent.putExtra("teste", "teste !!!" )
            context.startActivity(intent)
        }

    }



    override fun getItemCount(): Int = topRatedMovies.size

    fun addData(list: List<TopRatedMovie>) {
        topRatedMovies.addAll(list)
    }
}





