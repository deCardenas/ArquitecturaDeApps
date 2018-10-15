package com.albireo.myapplication.service.repo

import android.util.Log
import com.albireo.myapplication.service.api.OmdbAPI
import com.albireo.myapplication.service.api.TheMovieDbAPI
import com.albireo.myapplication.service.data.Result
import io.reactivex.Observable

class MovieNetworkRepo(private val theMovieDbAPI: TheMovieDbAPI, private val omdbAPI: OmdbAPI) : IMovieRepo {
    private val result = ArrayList<Result>()
    private val countries = ArrayList<String>()
    private var lastTimeStamp = System.currentTimeMillis()

    companion object {
        const val CACHE_LIFETIME = 20 * 1000
    }

    override fun getResult(): Observable<Result> {
        if(isUpdated() && result.size!=0){
            return getResultFromCache()
        }else{
            lastTimeStamp = System.currentTimeMillis()
            result.clear()
            return getResultFromNetwork()
        }
        //return getResultFromCache().switchIfEmpty{ getResultFromNetwork() }
    }

    override fun getResultFromNetwork(): Observable<Result> =
            theMovieDbAPI.getTopRatedMovies(1)
                    .flatMap { return@flatMap Observable.fromIterable(it.results) }
                    .doOnNext { result.add(it)
                        Log.d("RxJava", it.title) }

    override fun getResultFromCache(): Observable<Result> {
        /*if (isUpdated())
            return Observable.fromIterable(result)
        else{
            lastTimeStamp = System.currentTimeMillis()
            result.clear()
            return Observable.empty()
        }*/
        return Observable.fromIterable(result)
    }

    override fun getCountry(): Observable<String> {
        if(isUpdated() && countries.size!= 0){
            return getCountryFromCache()
        } else{
            lastTimeStamp = System.currentTimeMillis()
            result.clear()
            return getCountryFromNetwork()
        }
        //return getCountryFromCache().switchIfEmpty { getCountryFromNetwork() }
    }

    override fun getCountryFromNetwork(): Observable<String> =
            getResult()
                    .flatMap { return@flatMap omdbAPI.getMovieDetail(it.title) }
                    .flatMap { return@flatMap Observable.just(it.country) }
                    .doOnNext { countries.add(it)
                    Log.d("RxJava",it)}

    override fun getCountryFromCache(): Observable<String> {
        /*if (isUpdated())
            return Observable.fromIterable(countries)
        else{
            lastTimeStamp = System.currentTimeMillis()
            countries.clear()
            return Observable.empty()
        }*/
        return Observable.fromIterable(countries)
    }

    private fun isUpdated(): Boolean = (System.currentTimeMillis() - lastTimeStamp) < CACHE_LIFETIME
}