package com.kinematik.themoviedb.themoviedbappclean.data.interactor

import com.kinematik.themoviedb.themoviedbappclean.data.interactor.usecase.GetMoviesUseCase
import javax.inject.Inject

class Interactor @Inject constructor(
    val getMoviesUseCase: GetMoviesUseCase
)