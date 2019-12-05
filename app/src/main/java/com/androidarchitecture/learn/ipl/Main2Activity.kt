package com.androidarchitecture.learn.ipl

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.AsyncTask
import android.R
import android.R.attr.button
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class Main2Activity : AppCompatActivity() {


    private val SERVER = "http://10.0.2.2:3000/"


    internal var onButtonClickListener: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View) {
            val request = HttpGetRequest()
            request.execute()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.androidarchitecture.learn.ipl.R.layout.activity_main2)


        contactServerButton.setOnClickListener(onButtonClickListener)
    }

    inner class HttpGetRequest : AsyncTask<Void, Void, String>() {
        internal val REQUEST_METHOD = "GET"
        internal val READ_TIMEOUT = 15000
        internal val CONNECTION_TIMEOUT = 15000


        override fun doInBackground(vararg params: Void): String {
            var result: String
            var inputLine: String

            try {
                // connect to the server
                val myUrl = URL(SERVER)
                val connection = myUrl.openConnection() as HttpURLConnection
                connection.setRequestMethod(REQUEST_METHOD)
                connection.setReadTimeout(READ_TIMEOUT)
                connection.setConnectTimeout(CONNECTION_TIMEOUT)
                connection.connect()

                // get the string from the input stream
                val streamReader = InputStreamReader(connection.getInputStream())
                val reader = BufferedReader(streamReader)
                val stringBuilder = StringBuilder()
                inputLine = reader.readLine()
                while (inputLine != null) {
                    stringBuilder.append(inputLine)
                    inputLine=reader.readLine()
                }
                reader.close()
                streamReader.close()
                result = stringBuilder.toString()

            } catch (e: IOException) {
                e.printStackTrace()
                result = "error"
            }

            return result
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            tvServerResponse!!.text = result
        }


    }
}
