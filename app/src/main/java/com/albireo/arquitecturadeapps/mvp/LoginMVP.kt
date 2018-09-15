package com.albireo.arquitecturadeapps.mvp

import com.albireo.arquitecturadeapps.service.data.User

interface LoginMVP {
    interface View{
        fun getFirstName(): String
        fun getLastName(): String
        fun setFirstName(firstName: String)
        fun setLastName(lastName: String)

        fun showUserUnavailable()
        fun showInputError()
        fun showUserSaved()
    }
    interface Presenter{
        fun setView(view: View)
        fun loginClicked()
        fun getCurrentUser()

    }
    interface Model{
        fun createUser(firstName: String, lastName: String)
        fun getUser(): User?
    }
}