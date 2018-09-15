package com.albireo.arquitecturadeapps.service.repo

import com.albireo.arquitecturadeapps.service.data.User

interface LoginRepo {
    fun saveUser(user: User)
    fun getUser(): User?
}