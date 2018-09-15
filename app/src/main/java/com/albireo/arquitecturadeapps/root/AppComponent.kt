package com.albireo.arquitecturadeapps.root

import com.albireo.arquitecturadeapps.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(target: LoginActivity)
}