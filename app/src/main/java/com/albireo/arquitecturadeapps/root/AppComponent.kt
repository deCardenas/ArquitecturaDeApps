package com.albireo.arquitecturadeapps.root

import com.albireo.arquitecturadeapps.views.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, LoginModule::class])
interface AppComponent {
    fun inject(target: LoginActivity)
}