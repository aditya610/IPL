package com.androidarchitecture.learn.ipl.auction

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidarchitecture.learn.ipl.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*
import android.os.AsyncTask
import android.os.CountDownTimer
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_biding.*
import android.widget.ArrayAdapter
import com.androidarchitecture.learn.ipl.MainActivity
import com.androidarchitecture.learn.ipl.`class`.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


class Biding : AppCompatActivity() {
val list=ArrayList<String>()
    lateinit var minuteUpdateReceiver: BroadcastReceiver

     var flag=1
     var max:String="0"
    var main=1
    private lateinit var time: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.androidarchitecture.learn.ipl.R.layout.activity_biding)
        val list = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, list)

        val key=intent.getStringExtra("key")
        val teamname=intent.getStringExtra("teamname")
        var type=intent.getStringExtra("type")
        Log.d("num",type)
        var info=""
        var value = 0

        tvteamname.text=teamname
        val db= FirebaseDatabase.getInstance().getReference()
        db.child("main").setValue("1")
         db.child("exit").setValue("1")
        if(type.equals("create")) {

            timer1("","","","")
            var flag1=0

                var num = genrandomnumbers()
                value = num
            db.child("live").setValue(num.toString());
            Log.d("num",type)
            db.child("player_info").child(value.toString()).addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {


                }

                override fun onDataChange(p0: DataSnapshot) {
                    val child=p0.getValue(player::class.java)
                    if(child!=null) {
                        tvplayername.text = child.name
                        tvprice.text = child.starting_bid.toString()
                        max=tvprice.text.toString()
                        info="Role : "+ child.role.toString()


                    }
                }
            })
            db.child("batters").child(value.toString()).addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {


                }

                override fun onDataChange(p0: DataSnapshot) {
                    val child=p0.getValue(batters::class.java)
                    if(child!=null) {
                        info=info+"\nBatting : \n"+ "Runs : " +child.runs.toString()+", Avg : "+child.avg+", h_score : "+child.h_score+"\nHalf Century : "+child.half_century+", Fours : "+child.fours+"\nStrike rate : "+child.strike_rate
                    }
                }
            })
            db.child("bowlers").child(value.toString()).addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {


                }

                override fun onDataChange(p0: DataSnapshot) {
                    val child=p0.getValue(bowlers::class.java)
                    if(child!=null) {
                        info=info+"\nBowling : \n"+ "Wickets : " +child.wickets.toString()+", Economy : "+child.eco+"\n 3 fer : "+child.three_w+", Best Figures : "+child.bbw+"/"+child.bbr
                    }
                }
            })
            btnexit.visibility= View.VISIBLE
            btnnextplayer.visibility=View.VISIBLE
        }
        else if (type.equals("join")){

            var flag=1;

            db.child("main").addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {


                }

                override fun onDataChange(p0: DataSnapshot) {
                    val value=p0.getValue(String::class.java)
                    if (value != null) {
                        main=value.toInt()
                    }
                }

            })
           timer1("","","","")
            db.child("live").addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                   var num= p0.getValue(String::class.java)

                    if (num != null) {
                        value=num.toInt()
                        Log.d("num1",value.toString())
                        db.child("bid").child(teamname).setValue("0")
                        tvcurrentteam.text=""
                        tvcurrentbid.text=""
                        db.child("player_info").child(value.toString()).addValueEventListener(object :ValueEventListener{
                            override fun onCancelled(p0: DatabaseError) {


                            }

                            override fun onDataChange(p0: DataSnapshot) {
                                val child=p0.getValue(player::class.java)
                                if(child!=null) {
                                    tvplayername.text = child.name
                                    tvprice.text = child.starting_bid.toString()
                                    max=tvprice.text.toString()
                                    info="Role : "+ child.role.toString()

                                }
                                if(flag<=0 && main==1) {
                                    time.cancel()
                                    timer1("", "", "", "")
                                    btnbid.isEnabled=true
                                }
                                flag=flag-1;
                                db.child("main").setValue("1")
                            }
                        })
                        db.child("batters").child(value.toString()).addValueEventListener(object :ValueEventListener{
                            override fun onCancelled(p0: DatabaseError) {


                            }

                            override fun onDataChange(p0: DataSnapshot) {
                                val child=p0.getValue(batters::class.java)
                                if(child!=null) {
                                    info=info+"\nBatting : \n"+ "Runs : " +child.runs.toString()+", Avg : "+child.avg+", h_score : "+child.h_score+"\nHalf Century : "+child.half_century+", Fours : "+child.fours+"\nStrike rate : "+child.strike_rate

                                }
                            }
                        })
                        db.child("bowlers").child(value.toString()).addValueEventListener(object :ValueEventListener{
                            override fun onCancelled(p0: DatabaseError) {


                            }

                            override fun onDataChange(p0: DataSnapshot) {
                                val child=p0.getValue(bowlers::class.java)
                                if(child!=null) {
                                    info=info+"\nBowling : \n"+ "Wickets : " +child.wickets.toString()+", Economy : "+child.eco+"\n 3 fer : "+child.three_w+", Best Figures : "+child.bbw+"/"+child.bbr                                }
                            }
                        })

                    }
                }

            })
            Log.d("num",type)

            btnexit.visibility= View.INVISIBLE
            btnnextplayer.visibility=View.INVISIBLE

        }


               btnnextplayer.setOnClickListener({
                   db.child("bid").child(teamname).setValue("0")
                   tvcurrentteam.text=""
                   tvcurrentbid.text=""
                   btnbid.isEnabled=true
                   db.child("main").setValue("1")
                   time.cancel()
                   timer1("","","","")
                   var flag1=0

                   Log.d("id1",flag1.toString())

                       Log.d("id1",flag1.toString())
                   val list=ArrayList<String>(100)
                   db.child("player").addValueEventListener(object:ValueEventListener{
                       override fun onCancelled(p0: DatabaseError) {

                       }

                       override fun onDataChange(p0: DataSnapshot) {
                           val children=p0.children
                           list.clear()

                           Log.d("1234","enter")
                           for(child in children)
                           {
                               val value=child.getValue(String::class.java)
                               if(value!=null)
                               {
                                   Log.d("check1234",value.toString())
                                   list.add(value.toString())
                               }
                           }


                       }

                   })
                   var qq=1

                   while (qq==1) {
                       var num = genrandomnumbers()
                       value = num
                       var flag=1
                       for(i in list)
                       {
                           Log.d("check1234",i.toString())
                           if(i.toInt()==num)
                           {
                               flag =0
                           }
                       }
                       if(flag!=0)
                       {
                           qq=0
                           db.child("live").setValue(num.toString());
                           Log.d("check1234",num.toString())
                       }
                   }


                                       //db.child("live").setValue(num.toString());
                                       //Log.d("id1",num.toString())


                db.child("player_info").child(value.toString()).addValueEventListener(object :ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {


                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val child=p0.getValue(player::class.java)
                        if(child!=null) {
                            tvplayername.text = child.name
                            tvprice.text = child.starting_bid.toString()
                            max=tvprice.text.toString()
                            info="Role : "+ child.role.toString()
                        }
                    }
                })
                db.child("batters").child(value.toString()).addValueEventListener(object :ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {


                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val child=p0.getValue(batters::class.java)
                        if(child!=null) {
                            info=info+"\nBatting : \n"+ "Runs : " +child.runs.toString()+", Avg : "+child.avg+", h_score : "+child.h_score+"\nHalf Century : "+child.half_century+", Fours : "+child.fours+"\nStrike rate : "+child.strike_rate

                        }
                    }
                })
                db.child("bowlers").child(value.toString()).addValueEventListener(object :ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {


                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val child=p0.getValue(bowlers::class.java)
                        if(child!=null) {
                            info=info+"\nBowling : \n"+ "Wickets : " +child.wickets.toString()+", Economy : "+child.eco+"\n 3 fer : "+child.three_w+", Best Figures : "+child.bbw+"/"+child.bbr
                        }
                    }
                })
               //info=updatedata(teamname)

        })



        tvstats.setOnClickListener({
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Statistics")
            builder.setMessage(info)
        builder.create().show()
        })






        db.child("bid").child(teamname).setValue("0")
        db.child("login").child(key).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                   val children=p0.children
                list.clear()
                for(child in children)
                {
                    val value=child.getValue(String::class.java)
                    if (value != null) {
                        list.add(value)
                        list.notifyDataSetChanged()
                    }
                    Log.d("key1",value)
                }

            }

        })
        Log.d("key1",key)


        btnbid.setOnClickListener({
            Log.d("timer11","button")
            time.cancel()
            timer1("","",teamname,value.toString())
           // timer1("ram","","","")
    db.child("bid").child(teamname).setValue(etprice.text.toString())
    db.child("bid").addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            val children = p0.children
            for (child in children) {
                val value = child.getValue(String::class.java)
                if (value != null) {
                    if (value.toInt() > max.toInt()) {
                        val key = child.key
                        tvcurrentteam.text=key
                        max = value
                        Log.d("Max1", max)
                        tvcurrentbid.text = max

                    }
                }
            }
        }

    })

})


btnexit.setOnClickListener({
    db.child("exit").setValue("0")
})
        db.child("exit").addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val value=p0.getValue(String::class.java)
                if(value.equals("0"))
                {
                    val intent=Intent(this@Biding,MainActivity::class.java)
                    startActivity(intent)
                }
            }

        })




       // rvteams.layoutManager= RecyclerView.LayoutManager(this,1, RecyclerView.VERTICAL,false)
        rvteams.adapter=list


       /* //Retrofit
        val URL = "http://10.0.2.2:1563/"

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build();


         val builder = Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
             .client(okHttpClient)
            .build()

               val service=builder.create(server::class.java)
                val call=service.getbid()
               call.enqueue(object :Callback<String>{
                   override fun onFailure(call: Call<String>, t: Throwable) {
                       Toast.makeText(this@Biding,"Error Occurred"+t.toString(), Toast.LENGTH_LONG).show()

                       Log.d("Successful1",t.toString())

                   }

                   override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                        val data=response.body()
                        Log.d("Successful1",data)
                    }
                   }

               })*/


btnexit.setOnClickListener({
    db.child("exit").setValue("0")
})

    }
    fun timer1(name:String,price:String,team:String,no:String)
    {var flag1=1
        time=object :CountDownTimer(60000,1000){
            override fun onFinish() {
                //main=0

                timer.text="Left : 0"
                flag1=0
                btnbid.isEnabled=false
                Toast.makeText(this@Biding,"Time is over",Toast.LENGTH_LONG).show()
                val db=FirebaseDatabase.getInstance().getReference()
                db.child("main").setValue("0")
                //db.child("team").child(no.toString()).push().setValue(team.toString())
                //db.child("player").push().setValue(no)
                db.child("live").addValueEventListener(object :ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        var num = p0.getValue(String::class.java)
                        //main=0
                        if (num != null) {

                        }


                        db.child("bid").addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {

                            }

                            override fun onDataChange(p0: DataSnapshot) {
                                val children = p0.children
                                for (child in children) {
                                    val value = child.getValue(String::class.java)
                                    if (value != null) {
                                        if (value.toInt() >= max.toInt()) {
                                            val key = child.key
                                            max = value
                                            if(flag1==0) {
                                                //main=0;
                                                db.child("player_info").child(num.toString()).child("status").setValue(1)
                                                db.child("player_info").child(num.toString()).child("current_bid").setValue(max.toInt())
                                                db.child("player_info").child(num.toString()).child("team").setValue(key.toString())
                                                Log.d("id1","e"+ num.toString())
                                                db.child("player").child(num.toString()).setValue(num.toString())



                                            }

                                        }

                                        Log.d("id1","d"+ num.toString())
                                    }
                                    Log.d("id1","c"+ num.toString())
                                }
                                Log.d("id1", "b"+num.toString())

                            }


                        })
                        Log.d("id1", "a"+num.toString())
                    }
                })
                Log.d("id1","Hello")


            }

            override fun onTick(p0: Long) {
                timer.text="Left : "+p0/1000
            }}.start()
    }
    fun genrandomnumbers():Int
    {
        val r= Random();
        val t1=r.nextInt(80)
        return t1
    }
     public fun startMinuteUpdater() {
         val db= FirebaseDatabase.getInstance().getReference()
        val intentFilter =  IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        minuteUpdateReceiver = object :BroadcastReceiver(){
            override fun onReceive(contxt: Context?, intent: Intent?){
                max=tvprice.text.toString()

                db.child("bid").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val children = p0.children
                        for (child in children) {
                            val value = child.getValue(String::class.java)
                            if (value != null) {
                                if (value.toInt() > max.toInt()) {
                                    val key = child.key
                                    tvcurrentteam.text=key
                                    max = value
                                    Log.d("Max1", max)
                                    tvcurrentbid.text = max } } } } }) } }
         registerReceiver(minuteUpdateReceiver, intentFilter);
     }

    override fun onResume() {
        super.onResume()
        startMinuteUpdater()
    }

    override fun onPause() {
        super.onPause()
    unregisterReceiver(minuteUpdateReceiver)
    }
/*
    fun updatedata(teamname:String):String
  {
      var info=""
      val db=FirebaseDatabase.getInstance().getReference()
      db.child("bid").child(teamname).setValue("0")
      tvteamname.text=""
      tvcurrentbid.text=""
      db.child("live").addValueEventListener(object :ValueEventListener{
          override fun onCancelled(p0: DatabaseError) {

          }

          override fun onDataChange(p0: DataSnapshot) {
              var num= p0.getValue(String::class.java)
              if (num != null) {
                  val value=num.toInt()
                  Log.d("num1",value.toString())
                  db.child("player_info").child(value.toString()).addValueEventListener(object :ValueEventListener{
                      override fun onCancelled(p0: DatabaseError) {


                      }

                      override fun onDataChange(p0: DataSnapshot) {
                          val child=p0.getValue(player::class.java)
                          if(child!=null) {
                              tvplayername.text = child.name
                              tvprice.text = child.starting_bid.toString()
                              max=tvprice.text.toString()
                              info="Role : "+ child.role.toString()
                                  // timer1("","","","")
                                  timer1(child.name,max,"",value.toString())
                          }
                      }
                  })
                  db.child("batters").child(value.toString()).addValueEventListener(object :ValueEventListener{
                      override fun onCancelled(p0: DatabaseError) {


                      }

                      override fun onDataChange(p0: DataSnapshot) {
                          val child=p0.getValue(batters::class.java)
                          if(child!=null) {
                              info=info+"\nBatting : \n"+ "Runs : " +child.runs.toString()
                          }
                      }
                  })
                  db.child("bowlers").child(value.toString()).addValueEventListener(object :ValueEventListener{
                      override fun onCancelled(p0: DatabaseError) {


                      }

                      override fun onDataChange(p0: DataSnapshot) {
                          val child=p0.getValue(bowlers::class.java)
                          if(child!=null) {
                              info=info+"\nBowling : \n"+ "Wickets : " +child.wickets.toString()
                          }
                      }
                  })
              }

          }

      })
      return info
  }
*/
}
