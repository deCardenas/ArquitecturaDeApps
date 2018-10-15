package com.albireo.myapplication.di

import com.albireo.myapplication.models.MoviesActivityModel
import com.albireo.myapplication.mvpinterfaces.IMovies
import com.albireo.myapplication.presenters.MoviesActivityPresenter
import com.albireo.myapplication.service.api.OmdbAPI
import com.albireo.myapplication.service.api.TheMovieDbAPI
import com.albireo.myapplication.service.repo.IMovieRepo
import com.albireo.myapplication.service.repo.MovieNetworkRepo
import dagger.Module
import dagger.Provides
@Module
class MoviesActivityModule{
    @Provides
    fun provideMoviesPresenter(model: IMovies.Model): IMovies.Presenter = MoviesActivityPresenter(model)

    @Provides
    fun provideMoviesModel(repo: IMovieRepo): IMovies.Model = MoviesActivityModel(repo)

    @Provides
    fun provideMovieRepo(theMovieDbAPI: TheMovieDbAPI, omdbAPI: OmdbAPI): IMovieRepo =
            MovieNetworkRepo(theMovieDbAPI, omdbAPI)
}