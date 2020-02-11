package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kinematik.themoviedb.domain.common.DataResult
import com.kinematik.themoviedb.domain.entity.Movie
import com.kinematik.themoviedb.themoviedbappclean.farmework.interactor.MoviesInteractorImp
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.mapper.MoviePresentationMapper
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.model.MoviePresentationDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class OngoingMoviesViewModel @Inject constructor(private val moviesInteractorImp: MoviesInteractorImp) :
    ViewModel() {

    var loadingJob: Job? = null

    private var _dataPaged: LiveData<DataResult<PagedList<MoviePresentationDao>>>
    val dataPaged: LiveData<DataResult<PagedList<MoviePresentationDao>>>
        get() = _dataPaged

    private val reloadData  = MutableLiveData<Int>()

    init {
        val trans = Transformations.switchMap(reloadData, {
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val calendar = Calendar.getInstance()

            val currentDate = calendar.time

            calendar.add(Calendar.DAY_OF_YEAR, -14)

            val twoWeeksLater = calendar.time

            val currentDateString: String = format.format(currentDate)
            val twoWeeksLaterDateString: String = format.format(twoWeeksLater)

            return@switchMap observePagedSets(
                true,
                twoWeeksLaterDateString,
                currentDateString,
                1,
                20,

                viewModelScope

            )
        })

        _dataPaged = Transformations.switchMap(trans, {
            transform(it)
        })
        load()
    }

    private val _data: MutableLiveData<DataResult<List<MoviePresentationDao>>> = MutableLiveData()
    val data: LiveData<DataResult<List<MoviePresentationDao>>>
        get() = _data





    fun onAddToFavourites(item: MoviePresentationDao) {

    }

    fun onShare(item: MoviePresentationDao) {

    }

    fun load() {
        loadingJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }

        loadingJob = viewModelScope.launch() {
            //loadFromHead()
            reloadData.postValue(0)
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

    private suspend fun loadPaged(){
        //_dataPaged.postValue(DataResult.loading())

        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()

        val currentDate = calendar.time

        calendar.add(Calendar.DAY_OF_YEAR, -14)

        val twoWeeksLater = calendar.time

        val currentDateString: String = format.format(currentDate)
        val twoWeeksLaterDateString: String = format.format(twoWeeksLater)

        /*val mediatorLiveData = MediatorLiveData<LiveData<DataResult<PagedList<MoviePresentationDao>>>>()
        mediatorLiveData.addSource()*/

        _dataPaged = Transformations.switchMap(observePagedSets(
            true,
            twoWeeksLaterDateString,
            currentDateString,
            1,
            20,

            viewModelScope

        ), {
            transform(it)

        })
    }

    fun transform(data :PagedList<MoviePresentationDao>):MutableLiveData<DataResult<PagedList<MoviePresentationDao>>>{
        return MutableLiveData(DataResult.success(data))
    }


    fun observePagedSets(
        connectivityAvailable: Boolean,
        dateFrom: String,
        dateTo: String,
        page: Int = 1,
        pageSize: Int = 20,
        coroutineScope: CoroutineScope
    ) = /*if (connectivityAvailable) */observeRemotePagedSets(
        dateFrom,
        dateTo,
        page,
        pageSize,
        coroutineScope
    )
    /*else observeLocalPagedSets(dateFrom, dateTo, page, pageSize)*/

    /*private fun observeLocalPagedSets(
        dateFrom: String,
        dateTo: String,
        page: Int = 1,
        pageSize: Int = 20
    ): LiveData<PagedList<Movie>> {
        val dataSourceFactory =
            if (themeId == null) dao.getPagedLegoSets()
            else dao.getPagedLegoSetsByTheme(themeId)

        return LivePagedListBuilder(
            dataSourceFactory,
            LegoSetPageDataSourceFactory.pagedListConfig()
        ).build()
    }*/

    private fun observeRemotePagedSets(
        dateFrom: String,
        dateTo: String,
        page: Int = 1,
        pageSize: Int = 20,
        ioCoroutineScope: CoroutineScope
    )
            : LiveData<PagedList<MoviePresentationDao>> {
        val dataSourceFactory = LegoSetPageDataSourceFactory(
            dateFrom,
            dateTo,
            page,
            pageSize,
            moviesInteractorImp.remoteDataSource,
            moviesInteractorImp.localDataBaseDataSource,
            MoviePresentationMapper(),
            ioCoroutineScope
        )
        return LivePagedListBuilder(
            dataSourceFactory,
            LegoSetPageDataSourceFactory.pagedListConfig()
        ).build()
    }

}