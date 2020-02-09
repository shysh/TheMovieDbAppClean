package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies

import androidx.lifecycle.*
import com.kinematik.themoviedb.domain.common.DataResult
import com.kinematik.themoviedb.themoviedbappclean.farmework.interactor.MoviesInteractorImp
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.mapper.MoviePresentationMapper
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.model.MoviePresentationDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class OngoingMoviesViewModel @Inject constructor(private val moviesInteractorImp: MoviesInteractorImp) :
    ViewModel() {

    private val _data: MutableLiveData<DataResult<List<MoviePresentationDao>>> = MutableLiveData()
    val data: LiveData<DataResult<List<MoviePresentationDao>>>
        get() = _data


    fun onAddToFavourites(item: MoviePresentationDao) {

    }

    fun onShare(item: MoviePresentationDao) {

    }

    fun load() {
        viewModelScope.launch {
            loadFromHead()
        }
    }

    private suspend fun loadFromHead() {

        //todo
        /*Transformations.switchMap(resultListData(), result ->{

        })*/



        _data.postValue(DataResult.loading())

        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()

        val currentDate = calendar.time

        calendar.add(Calendar.DAY_OF_YEAR, -14)

        val twoWeeksLater = calendar.time

        val currentDateString: String = format.format(currentDate)
        val twoWeeksLaterDateString: String = format.format(twoWeeksLater)


        var result: DataResult<List<MoviePresentationDao>> = DataResult()

        val networkResult =
            moviesInteractorImp.getMovies(twoWeeksLaterDateString, currentDateString, 1, 20)
        when (networkResult.status) {
            DataResult.Status.SUCCESS -> {
                networkResult.data?.results?.let {
                    result =
                        DataResult.success(it.map { MoviePresentationMapper().mapFromEntity(it) })
                }


            }
            DataResult.Status.ERROR -> {

            }
        }

        _data.postValue(result)

    }

    fun resultListData(
        loadFromDd: () -> List<MoviePresentationDao>,
        networkCall: () -> DataResult<List<MoviePresentationDao>>
    ): LiveData<DataResult<List<MoviePresentationDao>>> {
        return liveData(Dispatchers.IO) {
            emit(DataResult.loading())


        }
    }

}