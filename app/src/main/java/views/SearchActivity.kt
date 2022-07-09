package views

import adapters.MoviesAdapter
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omega.moviedatabase.R
import network.models.Movie
import repositories.MoviesRepo
import viewmodels.MoviesViewModel
import viewmodels.MoviesViewModelFactory

class SearchActivity : AppCompatActivity() {
    private lateinit var search: android.widget.SearchView
    private lateinit var recycler: RecyclerView
    private lateinit var progress: ProgressBar
    private val moviesList: MutableList<Movie> = arrayListOf()
    private lateinit var adapter: MoviesAdapter
    private var page: Int = 1
    var queryText: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        getViews()

        // ViewModel Implementation
        val moviesViewModelFactory = MoviesViewModelFactory(MoviesRepo())
        val moviesViewModel =
            ViewModelProvider(this, moviesViewModelFactory)[MoviesViewModel::class.java]
        moviesViewModel.searchAllMovies(page, "")
        moviesViewModel.movies.observe(this) {
            moviesList.addAll(it)
        }

        // Recycler View UI Settings
        recycler.layoutManager = GridLayoutManager(this, 2)
        adapter = MoviesAdapter(moviesList)
        recycler.adapter = adapter

        // Live Search
        search.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                queryText = p0!!
                moviesViewModel.searchAllMovies(1, p0!!)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })

        // Infinite Scrolling
        moviesViewModel.movies.observeForever {
            adapter.notifyDataSetChanged()
            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        page++
                        moviesViewModel.searchAllMovies(page,queryText)
                    }
                }
            })
        }

    }

    private fun getViews() {
        search = findViewById(R.id.movies_search)
        recycler = findViewById(R.id.search_recycler)
        progress = findViewById(R.id.search_progress)
    }
}