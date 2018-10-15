package com.albireo.arquitecturadeapps.root

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(app: Application): Context
}