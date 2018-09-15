package com.albireo.arquitecturadeapps.presenters

import android.support.annotation.Nullable
import com.albireo.arquitecturadeapps.mvp.LoginMVP
import com.albireo.arquitecturadeapps.service.data.User

class LoginPresenter(var model: LoginMVP.Model) : LoginMVP.Presenter {
    @Nullable
    private var view: LoginMVP.View? = null

    override fun setView(view: LoginMVP.View) {
        this.view = view
    }

    override fun loginClicked() {
        if (view != null) {
            val firstName = view!!.getFirstName().trim()
            val lastName = view!!.getLastName().trim()
            if (firstName.isEmpty() || lastName.isEmpty()) {
                view!!.showInputError()
            } else {
                model.createUser(firstName, lastName)
                view!!.showUserSaved()
            }
        }
    }

    override fun getCurrentUser() {
        val user: User? = model.getUser()
        if (view != null) {
            if (user == null) {
                view!!.showUserUnavailable()
            } else {
                view!!.setFirstName(user.firstName)
                view!!.setLastName(user.lastName)
            }
        }
    }
}