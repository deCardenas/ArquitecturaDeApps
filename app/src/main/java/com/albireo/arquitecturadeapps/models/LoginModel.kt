package com.albireo.arquitecturadeapps.models

import com.albireo.arquitecturadeapps.mvp.LoginMVP
import com.albireo.arquitecturadeapps.service.data.User
import com.albireo.arquitecturadeapps.service.repo.LoginRepo

class LoginModel(var repo: LoginRepo) : LoginMVP.Model {


    override fun createUser(firstName: String, lastName: String) {
        repo.saveUser(User(0,firstName,lastName))
    }

    override fun getUser(): User? {
        return repo.getUser()
    }
}