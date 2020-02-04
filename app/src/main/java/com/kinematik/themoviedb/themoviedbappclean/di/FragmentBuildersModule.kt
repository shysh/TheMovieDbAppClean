package com.elifox.legocatalog.di

import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.favourites.FavouriteMoviesFragment
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.HomeFragment
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies.OngoingMoviesFragment
import com.kinematik.themoviedb.app.view.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeOngoingMoviesFragment(): OngoingMoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeFavouriteMoviesFragment(): FavouriteMoviesFragment
}
