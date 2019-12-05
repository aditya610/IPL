package com.androidarchitecture.learn.ipl

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import android.accounts.Account



class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        bottomNavigationView.selectedItemId=R.id.bnhome
    }
    val fragmentHome=Fragment_home()
    val fragmentMore=Fragment_more()
    val fragmentAuction=Fragment_auction()
    val fragmentSearch=Fragment_search()

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
   when(p0.itemId)
   {
       R.id.bnhome -> {
           supportFragmentManager.beginTransaction().replace(R.id.flmain, fragmentHome).commit()
       return true
       }
       R.id.bnauction -> {
           supportFragmentManager.beginTransaction().replace(R.id.flmain, fragmentAuction).commit()
           return true
       }
       R.id.bnmore -> {
           supportFragmentManager.beginTransaction().replace(R.id.flmain, fragmentMore).commit()
           return true
       }
       R.id.bnsearch -> {
           supportFragmentManager.beginTransaction().replace(R.id.flmain, fragmentSearch).commit()
           return true
       }

   }
        return false
    }

}
