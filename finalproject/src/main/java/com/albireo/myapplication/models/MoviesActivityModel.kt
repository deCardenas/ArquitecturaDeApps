package com.albireo.myapplication.models

import android.util.Log
import com.albireo.myapplication.mvpinterfaces.IMovies
import com.albireo.myapplication.service.data.MovieItem
import com.albireo.myapplication.service.repo.IMovieRepo
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class MoviesActivityModel(private val repo: IMovieRepo) : IMovies.Model {
    override fun getMovieItems(): Observable<MovieItem> {
        return Observable.zip(repo.getResult(),repo.getCountry(),
                BiFunction { t1, t2 ->
                    Log.d("NewMovie","result: ${t1.title}, country: $t2")
                    return@BiFunction MovieItem(t1.title, t2) })
    }
}