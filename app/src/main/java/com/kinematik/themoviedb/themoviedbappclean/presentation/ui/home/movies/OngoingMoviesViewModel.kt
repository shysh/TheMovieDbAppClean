package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.model.Movie
import javax.inject.Inject

class OngoingMoviesViewModel @Inject constructor() : ViewModel() {


    private val _query = MutableLiveData<Movie>()

    val query : LiveData<Movie> = _query

}