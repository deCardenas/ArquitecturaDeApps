package com.albireo.arquitecturadeapps.service.repo

import com.albireo.arquitecturadeapps.service.data.User

class MemoryRepo : LoginRepo {
    private var user: User? = null

    override fun saveUser(user: User) {
        this.user = user
    }

    override fun getUser(): User? = user
}