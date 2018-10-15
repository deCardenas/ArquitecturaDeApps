package com.albireo.myapplication.di

import com.albireo.myapplication.views.MoviesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MoviesActivityModule::class, OmdbApiModule::class, TmdbApiModule::class])
    abstract fun bindMoviesActivity(): MoviesActivity
}