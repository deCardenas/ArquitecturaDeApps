package com.albireo.myapplication.di

import com.albireo.myapplication.service.api.OmdbAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class OmdbApiModule {
    companion object {
        const val BASE_URL = "http://www.omdbapi.com/"
        const val API_KEY = "9c53beb6"
        const val OMDB ="OmdbAPI"
    }

    @Provides
    fun provideOmdbAPI(@Named(OMDB) retrofit: Retrofit): OmdbAPI = retrofit.create(OmdbAPI::class.java)

    @Provides
    @Named(OMDB)
    fun provideRetrofit(@Named(OMDB) client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Named(OMDB)
    fun provideClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor {
            val request = it.request()
            val url = request.url().newBuilder().addQueryParameter("apikey", API_KEY).build()
            return@addInterceptor it.proceed(request.newBuilder().url(url).build())
        }.build()
    }
}