package com.kinematik.themoviedb.themoviedbappclean.di

import android.app.Application
import com.elifox.legocatalog.di.ViewModelModule
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.MoviesRoomDataBase
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.MoviesApiService
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.interceptor.AuthInterceptor

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
    fun provideRemoteApiService(@MovieDbAPI okhttpClient: OkHttpClient,
                           converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, MoviesApiService::class.java)

    private fun <T> provideService(okhttpClient: OkHttpClient,
                                   converterFactory: GsonConverterFactory, clazz: Class<T>): T {
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

    //todo
    /*@Provides
    fun provideRepository(legoService: MoviesApiService)
            = LegoSetRemoteDataSource(legoService)*/

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
    fun provideDb(app: Application) = MoviesRoomDataBase.getInstance(app)


    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

}
