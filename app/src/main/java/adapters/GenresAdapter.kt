package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omega.moviedatabase.R
import network.models.Genre

class GenresAdapter(private val genresList: MutableList<Genre>) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get current genre object
        val currentGenre = genresList[position]

        holder.name.text = currentGenre.name
    }

    override fun getItemCount(): Int {
        return genresList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.genre_title)
    }
}