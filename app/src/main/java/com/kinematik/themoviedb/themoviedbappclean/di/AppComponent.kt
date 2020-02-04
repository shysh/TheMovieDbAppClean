package com.kinematik.themoviedb.themoviedbappclean.di

import android.app.Application
import com.elifox.legocatalog.di.MainActivityModule
import com.kinematik.themoviedb.themoviedbappclean.presentation.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: App)
}
