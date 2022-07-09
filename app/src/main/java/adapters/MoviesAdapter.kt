package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.omega.moviedatabase.R
import network.models.Movie

class MoviesAdapter(private val moviesList: MutableList<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = moviesList[position]
        holder.image.load("http://image.tmdb.org/t/p/original" + currentMovie.image){
            crossfade(true)
        }
        holder.rating.text = currentMovie.rating.toString()
        if (!currentMovie.adult) {
            holder.adult.visibility = View.GONE
        } else {
            holder.adult.visibility = View.VISIBLE
        }

        holder.title.text = currentMovie.title
        holder.release.text = currentMovie.releaseDate
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.movie_image)
        val title: TextView = itemView.findViewById(R.id.movie_title)
        val rating: TextView = itemView.findViewById(R.id.movie_rate)
        val adult: LinearLayout = itemView.findViewById(R.id.movie_adult)
        val release: TextView = itemView.findViewById(R.id.movie_release)
    }
}