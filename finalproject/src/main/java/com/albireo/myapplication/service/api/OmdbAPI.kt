package com.albireo.myapplication.service.api

import com.albireo.myapplication.service.data.MovieData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbAPI {
    @GET("/")
    fun getMovieDetail(@Query("t") title: String): Observable<MovieData>
}