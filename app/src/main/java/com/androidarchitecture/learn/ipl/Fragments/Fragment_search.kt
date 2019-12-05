package com.androidarchitecture.learn.ipl


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidarchitecture.learn.ipl.`class`.batters
import com.androidarchitecture.learn.ipl.`class`.bowlers
import com.androidarchitecture.learn.ipl.`class`.player
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.fragment_search.view.etsearch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Fragment_search : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_search, container, false)
view.btnsearch.setOnClickListener({
    val db=FirebaseDatabase.getInstance().getReference()
    var num="80";
    var info=""
     db.child("player_info").addValueEventListener(object :ValueEventListener{
         override fun onCancelled(p0: DatabaseError) {

         }

         override fun onDataChange(p0: DataSnapshot) {
             val children=p0.children
             for(child in children)
             {
                 val value=child.getValue(player::class.java)
                 if (value != null) {
                     if(etsearch.text.toString().equals(value.name)){
                         num=child.key.toString()
                         info="player Info:\n"+value.role
                     }


                 }
             }
             if(num.equals("80"))
             {

             }
             else {
                 db.child("batters").child(num.toString()).addValueEventListener(object : ValueEventListener {
                     override fun onCancelled(p0: DatabaseError) {


                     }

                     override fun onDataChange(p0: DataSnapshot) {
                         val child = p0.getValue(batters::class.java)
                         if (child != null) {
                             info = info + "\nBatting : \n" + "Runs : " + child.runs.toString() + ", Avg : " + child.avg + ", h_score : " + child.h_score + "\nHalf Century : " + child.half_century + ", Fours : " + child.fours + "\nStrike rate : " + child.strike_rate

                         }
                     }
                 })
                 db.child("bowlers").child(num.toString()).addValueEventListener(object : ValueEventListener {
                     override fun onCancelled(p0: DatabaseError) {


                     }

                     override fun onDataChange(p0: DataSnapshot) {
                         val child = p0.getValue(bowlers::class.java)
                         if (child != null) {
                             info = info + "\nBowling : \n" + "Wickets : " + child.wickets.toString() + ", Economy : " + child.eco + "\n 3 fer : " + child.three_w + ", Best Figures : " + child.bbw + "/" + child.bbr
                         }
                         val builder = AlertDialog.Builder(view.context)
                         builder.setTitle("Statistics")
                         builder.setMessage(info)
                         builder.create().show()

                     }
                 })


             }
         }

     })
})
        return view
    }


}
