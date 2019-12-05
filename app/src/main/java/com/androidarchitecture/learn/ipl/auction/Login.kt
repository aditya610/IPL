package com.androidarchitecture.learn.ipl.auction

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidarchitecture.learn.ipl.R
import com.androidarchitecture.learn.ipl.`class`.team
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnlogin.setOnClickListener({
            if(etteamname.text.isNotEmpty() && etpass.text.isNotEmpty() && etkey.text.isNotEmpty() && (rbjoin.isChecked||rbcreate.isChecked)) {
                val db = FirebaseDatabase.getInstance().getReference()
                db.child("signin").child(etteamname.text.toString()).addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val child = p0.getValue(team::class.java)
                        if (etpass.text.toString().equals(child?.teampassword.toString())) {
                            Toast.makeText(this@Login, "SUCCESSFULL LOGIN", Toast.LENGTH_SHORT).show()
                            val actintent = Intent(this@Login, Biding::class.java)
                            actintent.putExtra("key", etkey.text.toString())
                            actintent.putExtra("teamname", etteamname.text.toString())
                            if (rbjoin.isChecked) {
                                actintent.putExtra("type", "join")
                                Log.d("num", "join1")
                            } else if (rbcreate.isChecked) {
                                actintent.putExtra("type", "create")
                                Log.d("num", "create1")
                            } else {
                                actintent.putExtra("type", "null")
                            }
                            startActivity(actintent)
                        } else {
                            Toast.makeText(this@Login, "Wrong Password", Toast.LENGTH_SHORT).show()

                        }

                    }

                })
                db.child("login").child(etkey.text.toString()).child(etteamname.text.toString())
                    .setValue(etteamname.text.toString())
            }
            else{
                Toast.makeText(this,"INCORRECT INFORMATION",Toast.LENGTH_SHORT).show()
            }
            })


        
        }

    }

