package com.albireo.arquitecturadeapps.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.albireo.arquitecturadeapps.R
import com.albireo.arquitecturadeapps.api.TwitchAPI
import com.albireo.arquitecturadeapps.mvp.LoginMVP
import com.albireo.arquitecturadeapps.root.App
import com.albireo.arquitecturadeapps.service.data.Game
import com.albireo.arquitecturadeapps.service.data.Twitch
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginMVP.View {

    @Inject
    lateinit var presenter: LoginMVP.Presenter

    @Inject
    lateinit var twitchAPI: TwitchAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            presenter.loginClicked()
        }

        //Example: Using Twitch API with retrofit
        /*val call = twitchAPI.getTopGames("6nuayzqgonb2g15l48g1g17x06hzyd",40)
        call.enqueue(object : Callback<Twitch>{
            override fun onFailure(call: Call<Twitch>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Twitch>, response: Response<Twitch>) {
                val topGames = response.body()!!.data
                for (game in topGames){
                    System.out.println(game.name)
                }
            }
        })*/
        twitchAPI.getTopGamesObservable("6nuayzqgonb2g15l48g1g17x06hzyd").flatMap(object : Function<Twitch, Observable<Game>> {
            override fun apply(t: Twitch): Observable<Game> {
                return Observable.fromIterable(t.data)
            }
        }).flatMap { t -> Observable.just(t.name) }.filter(Predicate<String> {
            return@Predicate it.contains("w") || it.contains("W")
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: String) {
                        System.out.println("RxJava: " + t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })

    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.getCurrentUser()
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
        Toast.makeText(this, R.string.error_user_unavailable, Toast.LENGTH_SHORT).show()
    }

    override fun showInputError() {
        Toast.makeText(this, R.string.error_user_input, Toast.LENGTH_SHORT).show()
    }

    override fun showUserSaved() {
        Toast.makeText(this, R.string.user_saved, Toast.LENGTH_SHORT).show()
    }
}