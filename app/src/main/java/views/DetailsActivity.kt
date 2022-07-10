package views

import adapters.GenresAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omega.moviedatabase.R
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import repositories.MoviesRepo
import viewmodels.MoviesViewModel
import viewmodels.MoviesViewModelFactory

class DetailsActivity : AppCompatActivity() {
    private lateinit var carousel: ImageCarousel
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var budget: TextView
    private lateinit var releaseDate: TextView
    private lateinit var runtime: TextView
    private lateinit var genres: RecyclerView
    private lateinit var language: TextView
    private lateinit var progress: ProgressBar
    private lateinit var layout: ScrollView
    private lateinit var adapter: GenresAdapter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        getViews()
        layout.visibility = View.INVISIBLE

        // Get movie ID from adapter
        val intent = intent

        // ViewModel Implementation
        val moviesViewModelFactory = MoviesViewModelFactory(MoviesRepo())
        val moviesViewModel =
            ViewModelProvider(this, moviesViewModelFactory)[MoviesViewModel::class.java]
        moviesViewModel.getMovie(intent.getIntExtra("movieId", 0))
        moviesViewModel.movieDetails.observe(this) {

            progress.visibility = View.GONE
            layout.visibility = View.VISIBLE

            // Data Processing
            title.text = it.title
            description.text = it.description

            budget.text = "Budget: $".plus(it.budget.toString())
            releaseDate.text = "Release date: ".plus(it.releaseDate)
            runtime.text = "Runtime: ".plus(it.runTime).plus(" minutes")

            carousel.registerLifecycle(this)
            val carouselList: MutableList<CarouselItem> = arrayListOf()
            carouselList.add(CarouselItem("http://image.tmdb.org/t/p/original" + it.imageMain))
            carouselList.add(CarouselItem("http://image.tmdb.org/t/p/original" + it.imageBack))
            carousel.setData(carouselList)

            adapter = GenresAdapter(it.genres)
            genres.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            genres.adapter = adapter

            language.text = "Original language: ".plus(it.originalLanguage)

        }


    }

    private fun getViews() {
        carousel = findViewById(R.id.carousel)
        title = findViewById(R.id.movieDetails_name)
        description = findViewById(R.id.movieDetails_description)
        budget = findViewById(R.id.movieDetails_budget)
        releaseDate = findViewById(R.id.movieDetails_release)
        runtime = findViewById(R.id.movieDetails_runtime)
        genres = findViewById(R.id.movieDetails_genres)
        language = findViewById(R.id.movieDetails_language)
        progress = findViewById(R.id.movieDetails_progress)
        layout = findViewById(R.id.movieDetails_layout)
    }
}