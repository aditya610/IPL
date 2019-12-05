package com.androidarchitecture.learn.ipl.`class`

import android.util.Log
import com.androidarchitecture.learn.ipl.R

data class news (
    val title:String,
    val info:String,
    val image:Int
)

val newstitle= arrayOf("IPL 2020 auction to be held in Kolkata on December 19: Report","Kings XI Punjab set sight on Michael Hussey for head coach's role ahead of IPL 2020: Report","IPL 2020: Ravichandran Ashwin set to join Delhi Capitals","Rajasthan Royals’ English academy: a glimpse of cricket’s future?")
val newsinfo= arrayOf("Even as the trading window has been busier than previous years, a mini auction will be held ahead of the 13th edition of Indian Premier League (IPL). Unlike last year, the auction is all set to be held in Kolkata.\n" +
        "The auction for IPL 2020 will be held on December 19 at the new venue. The trading window that is currently open will close on November 14, according to ESPNCricinfo.\n" +
        "All 8 franchises in IPL were informed about the closing date of the trading window, according to the report. While they were allotted Rs 82 crore each for the IPL 2019, Rs 85 crore was allocated for the 2020 season.\n" +
        "Apart from an additional purse of Rs 3 crore, teams will also be allowed to use the money that was not spent during the previous IPL auction. Royal Challengers Bangalore have the least funds remaining from last season (Rs 1.8 crore) while Rajasthan Royals top the list with Rs 7.7 crore.\n"
    ,
    "Kings XI Punjab set sight on Michael Hussey for head coach's role ahead of IPL 2020: Report\n" +
            "Kings XI Punjab's hunt for maiden Indian Premier League (IPL) title continues as they gear up for another busy pre-tournament period where the franchise would be expected to make some significant movements in the auction. KXIP skipper Ravichandran Ashwin is reportedly on his way to the Delhi Capitals (DC). It has now also been learnt that the Punjab franchise is looking at former Australian batsman Mike Hussey as the new head coach of the team.\n" +
            "As per a report in Mumbai Mirror, Hussey, who is presently associated with the Chennai Super Kings as their batting coach, isn't the only prominent name that the Kings XI are eyeing for the job and have also shortlisted Andy Flower, Darren Lehman and George Bailey.\n"
    ,
    "Ace Indian off-spinner Ravichandran Ashwin is all set to make a switch from Kings XI Punjab to Delhi Capitals for next edition of the Indian Premier League (IPL).\n" +
            "\"Yes, it was a work in progress and we were waiting for the signature of one of the KXIP directors before going ahead with the deal. The team management believes looking at the last season that he can play an integral role on the kind of wickets that we play on at home,\" a DC official told IANS.\n" +
            "Ashwin, who was signed by Kings XI Punjab at the 2018 auction for Rs 7.6 crore, led the franchise in 14 matches in this year's IPL edition and scalped 15 wickets.\n" +
            "Overall, the off-spinner has taken 125 wickets in 139 IPL matches at an economy rate of 6.79, which is the best among all Indian bowlers with 50-plus wickets.\n" +
            "KXIP have not made the IPL play-offs since 2014 when they finished as runners-up. The Punjab-based franchise has not been able to perform as per expectations ever since IPL's inaugural season in 2008 and are, along with Delhi, the two franchises, who have not been able to lift the trophy till now.\n"
    ,
    "Just a month to go, then, until the beginning of the end. Or the start of the beginning. Or something like that anyway. The last week in October will see English cricket’s inaugural draft, a first bolt of lightning shot through the Hundred’s shell-company teams. Unbidden, unprecedented, not to mention rootless and entirely commercial in nature; the franchises are coming. Not that this is completely new. Look hard enough and they’re already here.\n" +
            "Rewind to early August and down a winding lane, through a patch of woodland, in the middle of a new-build timber hangar a few miles off the A3, Jaydev Unadkat is trying to teach a nervous-looking 13-year-old left-arm seamer how to stop dragging his line down outside leg stump by lifting his front arm a little higher.\n"
)
val newsimages= arrayOf(R.drawable.news1,R.drawable.news2,R.drawable.news3,R.drawable.news4)

fun randomnews():ArrayList<news>
{
    val list=ArrayList<news>()
    for (i in 1..4)
    {val j=i-1;
        list.add(news(newstitle[j], newsinfo[j], newsimages[j]))
        Log.d("title", newstitle[j])
    }
    return list
}