package com.albireo.arquitecturadeapps.root

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class, TwitchModule::class])
interface AppComponent: AndroidInjector<DaggerApplication> {
    fun inject(target: App)
    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app: Application):Builder
        fun build(): AppComponent
    }
}