package com.albireo.myapplication.views

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.albireo.myapplication.R
import com.albireo.myapplication.mvpinterfaces.IMovies
import com.albireo.myapplication.service.api.OmdbAPI
import com.albireo.myapplication.service.api.TheMovieDbAPI
import com.albireo.myapplication.service.data.MovieItem
import com.albireo.myapplication.service.repo.IMovieRepo
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

class MoviesActivity : AppCompatActivity(), IMovies.View {
    @Inject
    lateinit var presenter: IMovies.Presenter
    var movies = ArrayList<MovieItem>()
    lateinit var adapter : MovieItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        adapter= MovieItemAdapter(movies)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        recycler.setHasFixedSize(true)
        recycler.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.loadMovieItems()
    }

    override fun onPause() {
        super.onPause()
        presenter.setView(null)
        presenter.RxUnsuscribe()
        movies.clear()
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun updateData(movieItem: MovieItem) {
        movies.add(movieItem)
        adapter.notifyItemInserted(movies.size-1)
    }
}
