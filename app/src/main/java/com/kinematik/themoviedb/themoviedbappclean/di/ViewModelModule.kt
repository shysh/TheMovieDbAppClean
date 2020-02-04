package com.elifox.legocatalog.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.HomeViewModel
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.favourites.FavouriteMoviesViewModel
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies.OngoingMoviesViewModel
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.login.LoginViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OngoingMoviesViewModel::class)
    abstract fun bindOngoingMoviesViewModel(viewModel: OngoingMoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteMoviesViewModel::class)
    abstract fun bindFavouriteMoviesViewModel(viewModel: FavouriteMoviesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
