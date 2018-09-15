package com.albireo.arquitecturadeapps.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.albireo.arquitecturadeapps.R
import com.albireo.arquitecturadeapps.mvp.LoginMVP
import com.albireo.arquitecturadeapps.root.App
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() , LoginMVP.View {

    @Inject
    lateinit var presenter: LoginMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as App).getComponent().inject(this)

        btnLogin.setOnClickListener {
            presenter.loginClicked()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun getFirstName(): String = edtFirstName.text.toString()

    override fun getLastName(): String = edtLastName.text.toString()

    override fun setFirstName(firstName: String) {
        edtFirstName.setText(firstName)
    }

    override fun setLastName(lastName: String) {
        edtLastName.setText(lastName)
    }

    override fun showUserUnavailable() {
        Toast.makeText(this,R.string.error_user_unavailable,Toast.LENGTH_SHORT).show()
    }

    override fun showInputError() {
        Toast.makeText(this,R.string.error_user_input,Toast.LENGTH_SHORT).show()
    }

    override fun showUserSaved() {
        Toast.makeText(this,R.string.user_saved,Toast.LENGTH_SHORT).show()
    }
}