package com.androidarchitecture.learn.ipl


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.androidarchitecture.learn.ipl.auction.auctionactivity
import kotlinx.android.synthetic.main.fragment_auction.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Fragment_auction : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val li= inflater.inflate(R.layout.fragment_auction, container, false)
    li.btnstartauction.setOnClickListener({
        val intent= Intent(li.context,auctionactivity::class.java)
        startActivity(intent)

    })
        return li
    }


}
