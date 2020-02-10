package com.kinematik.themoviedb.themoviedbappclean.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kinematik.themoviedb.themoviedbappclean.farmework.cache.FileBasedCacheDataSource
import com.kinematik.themoviedb.themoviedbappclean.farmework.cache.FileBasedCacheManager
import com.kinematik.themoviedb.themoviedbappclean.farmework.cache.mapper.UserCacheEntityMapper
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.MoviesRoomDataBase
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.RoomLocalDataBaseDataSource
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.mapper.MovieDBEntityMapper
import com.kinematik.themoviedb.themoviedbappclean.farmework.interactor.MoviesInteractorImp
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.MoviesApiService
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.RetrofitRemoteDataSource
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.interceptor.AuthInterceptor
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.mapper.MovieResponseMapper
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.mapper.MoviesResponseMapper
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, DataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideRemoteApiService(
        @MovieDbAPI okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, MoviesApiService::class.java)

    private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory, clazz: Class<T>
    ): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }

    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MoviesApiService.ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @MovieDbAPI
    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder()
            .addInterceptor(AuthInterceptor()).build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) =
        Room.databaseBuilder(app, MoviesRoomDataBase::class.java, "movie-db.db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }
            })
            .build()

    @Singleton
    @Provides
    fun provideFileCache(app: Application) = FileBasedCacheManager.getInstance(app)

    @Singleton
    @Provides
    fun provideMovieEntityNetworkMapper() = MovieResponseMapper()

    @Singleton
    @Provides
    fun provideMoviesPageEntityNetworkMapper(mapper:MovieResponseMapper) = MoviesResponseMapper(mapper)

    @Singleton
    @Provides
    fun provideMovieDBEntityMapper() =  MovieDBEntityMapper()

    @Singleton
    @Provides
    fun provideUserCacheEntityMapper() =  UserCacheEntityMapper()

    @Singleton
    @Provides
    fun provideMoviesInteractor(fileBasedCacheDataSource: FileBasedCacheDataSource,
                                retrofitRemoteDataSource: RetrofitRemoteDataSource,
                                roomLocalDataBaseDataSource: RoomLocalDataBaseDataSource
    ) =  MoviesInteractorImp(fileBasedCacheDataSource, retrofitRemoteDataSource, roomLocalDataBaseDataSource)

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

}
