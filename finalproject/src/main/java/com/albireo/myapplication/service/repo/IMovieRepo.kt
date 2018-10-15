package com.albireo.myapplication.service.repo

import com.albireo.myapplication.service.data.Result
import io.reactivex.Observable

interface IMovieRepo {
    fun getResult(): Observable<Result>
    fun getResultFromNetwork(): Observable<Result>
    fun getResultFromCache(): Observable<Result>

    fun getCountry(): Observable<String>
    fun getCountryFromNetwork(): Observable<String>
    fun getCountryFromCache(): Observable<String>
}