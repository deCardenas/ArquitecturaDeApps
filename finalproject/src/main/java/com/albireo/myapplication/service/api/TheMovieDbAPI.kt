package com.albireo.myapplication.service.api

import com.albireo.myapplication.service.data.TopRatedMovies
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbAPI {
    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") page: Int) : Observable<TopRatedMovies>
}