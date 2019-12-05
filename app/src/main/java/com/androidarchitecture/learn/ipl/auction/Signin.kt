package com.androidarchitecture.learn.ipl.auction

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.androidarchitecture.learn.ipl.R
import com.androidarchitecture.learn.ipl.`class`.team
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signin.*

class Signin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signin)

            btnsignin.setOnClickListener({
                if(etpass.text.toString().equals(etpassc.text.toString()) && etteamname.text.isNotEmpty() && etpass.text.isNotEmpty() && etemailid.text.isNotEmpty() ) {
                    val obj = team(
                        etteamname.text.toString(),
                        etemailid.text.toString(),
                        etpass.text.toString(),
                        etbudget.text.toString(), 0
                    )

                    val db = FirebaseDatabase.getInstance().getReference()
                    db.child("signin").child(etteamname.text.toString()).setValue(obj).addOnSuccessListener {
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                    }
                }
                else{
                    Toast.makeText(this,"INCORRECT INFORMATION",Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }
}
