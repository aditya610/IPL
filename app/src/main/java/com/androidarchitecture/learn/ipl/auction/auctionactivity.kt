package com.androidarchitecture.learn.ipl.auction

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidarchitecture.learn.ipl.R
import kotlinx.android.synthetic.main.activity_auctionactivity.*

class auctionactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auctionactivity)
        btnsign.setOnClickListener({
            val actintent=Intent(this,Signin::class.java)
            startActivity(actintent)
        })
        btnlogin.setOnClickListener({
            val actintent=Intent(this,Login::class.java)
            startActivity(actintent)
        })
    }
}
