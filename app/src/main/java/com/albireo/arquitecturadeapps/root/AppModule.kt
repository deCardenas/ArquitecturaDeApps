package com.albireo.arquitecturadeapps.root

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(app: Application): Context
}