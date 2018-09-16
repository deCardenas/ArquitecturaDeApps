package com.albireo.arquitecturadeapps.root

import com.albireo.arquitecturadeapps.models.LoginModel
import com.albireo.arquitecturadeapps.mvp.LoginMVP
import com.albireo.arquitecturadeapps.presenters.LoginPresenter
import com.albireo.arquitecturadeapps.service.repo.LoginRepo
import com.albireo.arquitecturadeapps.service.repo.MemoryRepo
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    fun providePresenter(model: LoginMVP.Model): LoginMVP.Presenter = LoginPresenter(model)

    @Provides
    fun provideModel(repo: LoginRepo): LoginMVP.Model = LoginModel(repo)

    @Provides
    fun provideLoginRepo(): LoginRepo = MemoryRepo()
}