package com.kinematik.themoviedb.themoviedbappclean.di

import com.elifox.legocatalog.di.FragmentBuildersModule
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
