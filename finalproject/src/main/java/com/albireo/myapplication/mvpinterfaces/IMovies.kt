package com.albireo.myapplication.mvpinterfaces

import com.albireo.myapplication.service.data.MovieItem
import io.reactivex.Observable

interface IMovies {
    interface View{
        fun showSnackBar(message: String)
        fun updateData(movieItem: MovieItem)
    }
    interface Presenter{
        fun setView(view: View?)
        fun loadMovieItems()
        fun RxUnsuscribe()
    }
    interface Model{
        fun getMovieItems(): Observable<MovieItem>
    }
}