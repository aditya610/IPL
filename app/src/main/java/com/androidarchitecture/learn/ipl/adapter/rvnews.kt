package com.androidarchitecture.learn.ipl.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.androidarchitecture.learn.ipl.R
import com.androidarchitecture.learn.ipl.`class`.news

class rvnews(val list: ArrayList<news>):RecyclerView.Adapter<rvnews.viewholder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {
      val li:LayoutInflater=p0.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        val view=li.inflate(R.layout.no_of_news,p0,false)
        return viewholder(view)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(p0: viewholder, p1: Int) {
     p0.title.text=list[p1].title

        p0.info.text=list[p1].info
        Log.d("title",p0.info.text.toString())
        p0.images.setImageResource(list[p1].image)

    }

    class viewholder(val itemview:View):RecyclerView.ViewHolder(itemview)
    {
        val title=itemview.findViewById<TextView>(R.id.tvheading)
        val info=itemview.findViewById<TextView>(R.id.tvinfo)
        val images=itemview.findViewById<ImageView>(R.id.ivnews)


    }
}