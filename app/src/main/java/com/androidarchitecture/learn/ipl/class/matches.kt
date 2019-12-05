package com.androidarchitecture.learn.ipl.`class`

import com.androidarchitecture.learn.ipl.R
import java.util.*

data class matches (
    val team1:String,
    val team2:String,
    val title:String,
    val img1:Int,
    val img2:Int
    )

val team1= arrayOf("RR","DC","MI","CSK");
val team2= arrayOf("KXII","KKR","RCB","SRH");
val title="Match No ";
val img1= arrayOf(R.drawable.rr,R.drawable.dc,R.drawable.mi,R.drawable.csk)
val img2=arrayOf(R.drawable.kxii,R.drawable.kkr,R.drawable.rcb,R.drawable.srh)

fun genrandommatches(num:Int):ArrayList<matches>
{
    val matches=ArrayList<matches>(num)
    val r=Random();
    for(i in 1..num)
    { val t1=r.nextInt(4)
        val t2=r.nextInt(4)
        matches.add(matches(team1[t1], team2[t2], title+i,img1[t1],img2[t2]))
    }
    return matches
}

