package com.androidarchitecture.learn.ipl.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.androidarchitecture.learn.ipl.R
import com.androidarchitecture.learn.ipl.`class`.matches

class rvmatches(val list: ArrayList<matches>):RecyclerView.Adapter<rvmatches.viewholder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {
     val li:LayoutInflater=p0.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
     val view=li.inflate(R.layout.no_of_match,p0,false)
        return viewholder(view)
    }

    override fun getItemCount(): Int {
    return list.size
    }

    override fun onBindViewHolder(p0: viewholder, p1: Int) {
        p0.team1.text=list[p1].team1
        p0.team2.text=list[p1].team2
        p0.title.text=list[p1].title
        p0.img1.setImageResource(list[p1].img1)
        p0.img2.setImageResource(list[p1].img2)
    }

    class viewholder(val itemview:View):RecyclerView.ViewHolder(itemview)
    {
        val img1=itemview.findViewById<ImageView>(R.id.ivteam1)
        val img2=itemview.findViewById<ImageView>(R.id.ivteam2)
        val team1=itemview.findViewById<TextView>(R.id.tvteam1)
        val team2=itemview.findViewById<TextView>(R.id.tvteam2)
        val title=itemview.findViewById<TextView>(R.id.tvtitle)

    }
}