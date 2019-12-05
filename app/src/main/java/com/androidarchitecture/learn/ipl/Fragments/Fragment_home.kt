package com.androidarchitecture.learn.ipl


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.androidarchitecture.learn.ipl.`class`.genrandommatches
import com.androidarchitecture.learn.ipl.`class`.matches
import com.androidarchitecture.learn.ipl.`class`.randomnews
import com.androidarchitecture.learn.ipl.adapter.rvmatches
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Fragment_home : Fragment() {
val list=ArrayList<matches>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val li= inflater.inflate(R.layout.fragment_home, container, false)
       li.rvmatches.layoutManager=GridLayoutManager(li.context,1,RecyclerView.HORIZONTAL,false)
        val adapter=com.androidarchitecture.learn.ipl.adapter.rvmatches(genrandommatches(5))
        li.rvmatches.adapter=adapter
        li.rvnews.layoutManager=GridLayoutManager(li.context,1,RecyclerView.VERTICAL,false)
        val newsadapter=com.androidarchitecture.learn.ipl.adapter.rvnews(randomnews())
        li.rvnews.adapter=newsadapter
        return li;

    }


}
