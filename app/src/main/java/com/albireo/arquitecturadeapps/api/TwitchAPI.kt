package com.albireo.arquitecturadeapps.api

import com.albireo.arquitecturadeapps.service.data.Twitch
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TwitchAPI {
    @GET("games/top")
    fun getTopGames(@Header("Client-ID")clientId : String, @Query("first")first: Int): Call<Twitch>

    @GET("users")
    fun getUsers(): Call<Twitch>

    @GET("games/top")
    fun getTopGamesObservable(@Header("Client-ID")clientId: String) : Observable<Twitch>
}