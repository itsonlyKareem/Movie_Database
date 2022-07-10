package views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.helper.widget.Carousel
import androidx.lifecycle.ViewModelProvider
import com.omega.moviedatabase.R
import repositories.MoviesRepo
import viewmodels.MoviesViewModel
import viewmodels.MoviesViewModelFactory

class DetailsActivity : AppCompatActivity() {
    private lateinit var carousel: Carousel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        getViews()

        // Get movie ID from adapter
        val intent = intent

        // ViewModel Implementation
        val moviesViewModelFactory = MoviesViewModelFactory(MoviesRepo())
        val moviesViewModel = ViewModelProvider(this, moviesViewModelFactory)[MoviesViewModel::class.java]
        moviesViewModel.getMovie(intent.getIntExtra("movieId",0))
        moviesViewModel.movieDetails.observe(this) {
            println(it.title)
        }

        carousel.setAdapter(object : Carousel.Adapter{
            override fun count(): Int {
                return 0
            }

            override fun populate(view: View?, index: Int) {

            }

            override fun onNewItem(index: Int) {

            }

        })
    }

    private fun getViews() {
        carousel = findViewById(R.id.carousel)
    }
}