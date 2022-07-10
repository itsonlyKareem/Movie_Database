package viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import network.models.Genre
import network.models.Movie
import network.models.MovieDetails
import repositories.MoviesRepo

class MoviesViewModel (private val moviesRepo: MoviesRepo): ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO) {
            moviesRepo.getGenres()
        }
    }

    fun getMovies(genreId: Int, page: Int) {
        moviesRepo.getMovies(genreId,page)
    }

    fun searchAllMovies(page: Int, query:String) {
        moviesRepo.searchAll(page, query)
    }

    fun getMovie(movieId:Int) {
        moviesRepo.getMovie(movieId)
    }

    val genres: LiveData<MutableList<Genre>> = moviesRepo.genresLiveData
    val movies: LiveData<MutableList<Movie>> = moviesRepo.moviesLiveData
    val movieDetails: LiveData<MovieDetails> = moviesRepo.movieDetailsLiveData
}