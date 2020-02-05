package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kinematik.themoviedb.themoviedbappclean.farmework.interactor.MoviesInteractorImp
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.model.Movie
import javax.inject.Inject

class OngoingMoviesViewModel @Inject constructor(private val moviesInteractorImp: MoviesInteractorImp) : ViewModel() {


    private val _query = MutableLiveData<Movie>()

    /*val query : LiveData<PagedList<Movie>> = LivePagedListBuilder(moviesInteractorImp.getPagedCachedMovies(), 20)*/

    fun onAddToFavourites(item:Movie){

    }

    fun onShare(item:Movie){
        val i = moviesInteractorImp!=null
    }

}