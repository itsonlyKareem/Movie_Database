package fragments

import adapters.MoviesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omega.moviedatabase.R
import network.models.Movie
import repositories.MoviesRepo
import viewmodels.MoviesViewModel
import viewmodels.MoviesViewModelFactory

class MoviesFragment : Fragment() {

    private lateinit var moviesRecycler: RecyclerView
    lateinit var progress: ProgressBar
    private var genreName: String = ""
    var genreId: Int = 0
    var page = 1

    private lateinit var adapter: MoviesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var moviesList: MutableList<Movie> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getViews(view)

        // Obtaining arguments from PagerAdapter
        arguments?.getString("name")?.let {
            genreName = it
        }
        arguments?.getInt("id")?.let {
            genreId = it
        }

        // ViewModel Implementation
        val moviesViewModelFactory = MoviesViewModelFactory(MoviesRepo())
        val moviesViewModel =
            ViewModelProvider(this, moviesViewModelFactory)[MoviesViewModel::class.java]
        moviesViewModel.getMovies(genreId, 1)
        moviesViewModel.movies.observe(viewLifecycleOwner) {
            moviesList.addAll(it)
        }

        // Recycler View UI Settings
        layoutManager = GridLayoutManager(context, 2)
        moviesRecycler.layoutManager = layoutManager
        adapter = MoviesAdapter(moviesList)
        moviesRecycler.adapter = adapter


        moviesViewModel.movies.observeForever {
            adapter.notifyDataSetChanged()
            progress.visibility = View.GONE
            moviesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        progress.visibility = View.VISIBLE
                        page++
                        moviesViewModel.getMovies(genreId, page)
                    }
                }
            })
        }

        /*
        How Infinite Scrolling is implemented:
        1- The appropriate ViewModel is referenced and the method
        to fetch the movies is called.
        2- The loaded movies are observed once to obtain its data
        and added to the moviesList created exclusively for this
        class.
        3- The first batch of movies are fed to the adapter to view
        them first
        4- A Scroll listener is then attached to the Recycler View
        to detect whether is view is scrolled to its end.
        5- If the view's end is reached, the method to fetch the next
        page of movies is called and the ViewModel's list is reloaded
        with the new content.
        6- The new content is added to the existing moviesList and the
        adapter is notified with the new content is received to be able
        to load it seamlessly.
         */

    }

    private fun getViews(view: View) {
        moviesRecycler = view.findViewById(R.id.movies_recycler)
        progress = view.findViewById(R.id.movies_progress)
    }
}