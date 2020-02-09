package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies

import androidx.lifecycle.*
import com.kinematik.themoviedb.domain.common.DataResult
import com.kinematik.themoviedb.themoviedbappclean.farmework.interactor.MoviesInteractorImp
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class OngoingMoviesViewModel @Inject constructor(private val moviesInteractorImp: MoviesInteractorImp) :
    ViewModel() {

    private val _data: MutableLiveData<DataResult<List<Movie>>> = MutableLiveData()
    val data: LiveData<DataResult<List<Movie>>>
        get() = _data


    fun onAddToFavourites(item: Movie) {

    }

    fun onShare(item: Movie) {

    }

    suspend fun loadFromHead() {

        viewModelScope.launch {
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val calendar = Calendar.getInstance()

            val currentDate = calendar.time

            calendar.add(Calendar.DAY_OF_YEAR, -14)

            val twoWeeksLater = calendar.time

            val currentDateString: String = format.format(currentDate)
            val twoWeeksLaterDateString: String = format.format(twoWeeksLater)

            moviesInteractorImp.getMovies(currentDateString, twoWeeksLaterDateString, 1, 20)
        }





        /*resultListData(
            moviesInteractorImp.getCachedMovies(),
            moviesInteractorImp.getMovies())*/
    }

    fun resultListData(
        loadFromDd: () -> List<Movie>,
        networkCall: () -> DataResult<List<Movie>>
    ): LiveData<DataResult<List<Movie>>> {
        return liveData(Dispatchers.IO) {
            emit(DataResult.loading())


        }
    }

}