package com.albireo.arquitecturadeapps.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.albireo.arquitecturadeapps.R
import com.albireo.arquitecturadeapps.mvp.LoginMVP
import com.albireo.arquitecturadeapps.root.App
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , LoginMVP.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as App).getComponent().inject(this)

        btnLogin.setOnClickListener {

        }
    }
}