package viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import network.models.Genre
import network.models.Movie
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

    val genres: LiveData<MutableList<Genre>> = moviesRepo.genresLiveData
    val movies: LiveData<MutableList<Movie>> = moviesRepo.moviesLiveData
}