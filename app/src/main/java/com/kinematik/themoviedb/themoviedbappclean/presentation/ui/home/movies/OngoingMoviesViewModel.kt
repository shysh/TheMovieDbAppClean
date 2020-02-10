package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinematik.themoviedb.themoviedbappclean.farmework.MoviesRepository
import com.kinematik.themoviedb.themoviedbappclean.farmework.interactor.MoviesInteractorImp
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.model.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

class OngoingMoviesViewModel @Inject constructor(val repo:MoviesRepository) : ViewModel() {


    init {
        loadData()
    }


    fun loadData(){
        viewModelScope.launch {

        }
    }

    suspend fun loadMovies(page:Int, itemsCount:Int){



    }

    fun onAddToFavourites(item: Movie) {

    }

    fun onShare(item: Movie) {

    }

}