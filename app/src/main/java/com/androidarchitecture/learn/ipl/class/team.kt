package com.androidarchitecture.learn.ipl.`class`

data class team (
    val teamname:String,
    val teamemail:String,
    val teampassword:String,
    val teambudget:String,
    val teamplayers:Int ){
    constructor():this("","","","",0)
}

data class player(
    val current_bid:Int,
    val name:String,
    val player_id:Int,
    val role:String,
    val starting_bid:Int,
    val status:Int,
    val team:String ){
    constructor():this(0,"",0,"",0,0,"")
}

data class batters(
    val avg:String,
    val century:String,
    val fours:String,
    val h_score:String,
    val half_century:String,
    val id:String,
    val matches:String,
    val runs:String,
    val sixes:String,
    val strike_rate:String
){
    constructor():this("","","","","","","","","","")
}
data class bowlers(
    val avg:String,
    val bbr:String,
    val bbw:String,
    val eco:String,
    val id:String,
    val matches:String,
    val three_w:String,
    val wickets:String
){
    constructor():this("","","","","","","","")

}
