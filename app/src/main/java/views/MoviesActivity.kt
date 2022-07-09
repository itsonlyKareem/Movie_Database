package views

import adapters.PagerAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
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

    private lateinit var tabLayout: TabLayout
    private lateinit var pager: ViewPager2
    private lateinit var search: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        getViews()

        // Procedure of getting genres.
        val moviesViewModelFactory = MoviesViewModelFactory(MoviesRepo())
        val moviesViewModel =
            ViewModelProvider(this, moviesViewModelFactory)[MoviesViewModel::class.java]

        moviesViewModel.genres.observe(this) {
            val genresList = it
            genresList.add(0, Genre(0, "All"))
            val pagerAdapter = PagerAdapter(this, genresList)
            pager.adapter = pagerAdapter
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                tab.text = it[position].name
            }.attach()
        }

        search.setOnClickListener {
            val intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getViews() {
        tabLayout = findViewById(R.id.genres_tab)
        pager = findViewById(R.id.movies_pager)
        search = findViewById(R.id.search_card)
    }
}