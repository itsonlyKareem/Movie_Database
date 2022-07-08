package viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import repositories.MoviesRepo

class MoviesViewModelFactory(private val moviesRepo: MoviesRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesViewModel(moviesRepo) as T
    }
}