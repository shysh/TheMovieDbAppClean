package com.kinematik.themoviedb.themoviedbappclean.farmework.network.interceptor

import com.kinematik.themoviedb.themoviedbappclean.farmework.network.MoviesApiService
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * A {@see RequestInterceptor} that adds an api_key query parameter to requests
 */
class AuthInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        var request: Request = chain.request()
        val url: HttpUrl = request.url().newBuilder()
            .addQueryParameter(MoviesApiService.QUERY_PARAM_API_KEY, MoviesApiService.API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
