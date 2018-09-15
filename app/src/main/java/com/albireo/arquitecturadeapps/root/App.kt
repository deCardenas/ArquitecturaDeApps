package com.albireo.arquitecturadeapps.root

import android.app.Application

class App : Application(){
    private lateinit var component : AppComponent

    override fun onCreate() {
        super.onCreate()
        //build the project and then initialize the component
        component = DaggerAppComponent.builder().build()
    }

    fun getComponent() : AppComponent = component
}