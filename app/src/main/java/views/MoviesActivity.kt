package views

import adapters.PagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.omega.moviedatabase.R
import network.models.Genre
import repositories.MoviesRepo
import viewmodels.MoviesViewModel
import viewmodels.MoviesViewModelFactory

class MoviesActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var pager: ViewPager2
    lateinit var search: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        getViews()

        // Procedure of getting genres.
        val moviesViewModelFactory = MoviesViewModelFactory(MoviesRepo())
        val moviesViewModel =
            ViewModelProvider(this, moviesViewModelFactory)[MoviesViewModel::class.java]

        moviesViewModel.genres.observe(this, Observer {
            val genresList = it
            genresList.add(0, Genre(0,"All"))
            val pagerAdapter = PagerAdapter(this, genresList)
            pager.adapter = pagerAdapter
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                tab.text = it[position].name
            }.attach()
        })



    }

    private fun getViews() {
        tabLayout = findViewById(R.id.genres_tab)
        pager = findViewById(R.id.movies_pager)
        search = findViewById(R.id.search_card)
    }
}