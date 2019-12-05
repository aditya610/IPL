package com.androidarchitecture.learn.ipl.`class`

import android.telecom.Call
import retrofit2.http.GET

interface server {

    @GET("bid")
    fun getbid():retrofit2.Call<String>

}