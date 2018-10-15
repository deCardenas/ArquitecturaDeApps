package com.albireo.myapplication.presenters

import com.albireo.myapplication.mvpinterfaces.IMovies
import com.albireo.myapplication.service.data.MovieItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MoviesActivityPresenter(private val model: IMovies.Model) : IMovies.Presenter {
    private var view: IMovies.View? = null
    private var disposable: Disposable? = null
    override fun setView(view: IMovies.View?) {
        this.view = view
    }

    override fun loadMovieItems() {
        disposable = model.getMovieItems().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<MovieItem>() {
                    override fun onComplete() {
                        if (view != null) view!!.showSnackBar("Datos Cargados")
                    }

                    override fun onNext(t: MovieItem) {
                        if (view != null) view!!.updateData(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        if (view != null) view!!.showSnackBar(e.localizedMessage)
                    }
                })
    }

    override fun RxUnsuscribe() {
        if (disposable != null && !disposable!!.isDisposed) disposable?.dispose()
    }
}