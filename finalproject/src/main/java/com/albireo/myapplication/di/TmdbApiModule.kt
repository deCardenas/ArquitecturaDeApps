package com.albireo.myapplication.di

import com.albireo.myapplication.service.api.TheMovieDbAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class TmdbApiModule {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "0777a4784320df99f22cfb59888f5422"
        const val TMDB ="TmdbAPI"
    }

    @Provides
    fun provideTheMovieDbAPI(@Named(TMDB)retrofit: Retrofit): TheMovieDbAPI {
        return retrofit.create(TheMovieDbAPI::class.java)
    }

    @Provides
    @Named(TMDB)
    fun provideRetrofit(@Named(TMDB)client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Named(TMDB)
    fun provideClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor {
            var request =it.request()
            val url = request.url().newBuilder().addQueryParameter("api_key", API_KEY).build()
            request = request.newBuilder().url(url).build()
            return@addInterceptor it.proceed(request)
        }.build()
    }

}