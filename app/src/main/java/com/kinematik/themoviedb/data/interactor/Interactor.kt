package com.kinematik.themoviedb.data.interactor

import com.kinematik.themoviedb.data.interactor.usecase.GetMoviesUseCase
import javax.inject.Inject

class Interactor @Inject constructor(
    val getMoviesUseCase: GetMoviesUseCase
)