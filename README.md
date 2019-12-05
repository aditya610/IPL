# IPL
this is a ipl auction app using realtime firebase to connect multiplayer to bid for players  

1.1	General Introduction
Indian premier League (IPL) is one of the biggest cricketing league around the world .Players from different countries come to india to play in this league . IPL was starts in 2008 that was the first season of IPL people liked it so much till 2019 IPL has reached to 11 season.
It conducts every year but conducting IPL  is not an issue but issue is who will play in which team. For this issue BCCI conducts auction not every year but frequently. So before the starting league There is a auction in which players are being bid .
Big Businessmen have their team because they can afford to buy a great player. In Auction these Businessmen comes onsite having a Budget to buy or sell players. But it is a hectic process because go on site and spend a whole day there is lot of hectic.This is a very intense process which is carried out for 2 days . 
So to solve this problem or making it little bit easier and convienent to businessmens or we can say bidders developers are trying to make it online so that it can be access from anywhere. But main issue is of security so they are trying to give their best. Our team also give a hand to solve
this problem by making a small android application for conducting online IPL auction.



Implementation Details  

User first need to sign into to the server for which he is need to provide details which are uploaded on sign() key on database 
On real time database in firebase after that user login for which he need to provide a key if he want to join a session which is accessed only by person who create it which is uploaded on live()
On firebase after that credentials of user are confirmed by signin() key on firebase . then the
Data is uploaded to firebase on login() key.
Then a live() key is generated which is uploaded and fetched whenever a new player details are shown on screeb to maintain consistency on all devices
Player information is fetched from player_info() key
Player stats are displayed on alertdialogboxbuilder() which are fetched from the firebase in batters() 
And bowlers() key
Simultaneously a object of countdowntimer() is executed for sixty second 
and object of StartMinuteUpdate() is created in which BroadcastReciever are used to update the max bid() on ever new minute
user bid for the player and max bid is assign to player from bid() key on firebase
Now User can bid for next player which is only accessed by admin for which visibility() is used  or can finish the proccedings
Apart from this few more libraries are used which are
Intent , Array Adapter ,get randomnumbers
To design the UI material design library is used which includes:
Bottom Navigation view 
Apart from this Fragments  , recycleview , cardview ,toast etc are used
