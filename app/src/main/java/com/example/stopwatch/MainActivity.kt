package com.example.stopwatch

import android.graphics.Color
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.wifi.rtt.CivicLocationKeys.STATE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Chronometer


class MainActivity : AppCompatActivity() {

    //make a classwide static constant in Kotlin
    companion object {
        //all your static constants go here
        val TAG = "MainActivity"
        val STATE_TIME = "display time"
    }

    lateinit var start : Button
    lateinit var reset : Button
    lateinit var chro : Chronometer
    var running = false
    var time = 0L


    override fun onCreate(savedInstanceState: Bundle?) {

        //preserve state through orientation changes



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wirewidgets()

        //restore instance state if it exists
        if(savedInstanceState != null) {
            time = savedInstanceState.getLong(STATE_TIME)
            chro.base = SystemClock.elapsedRealtime() - time
        }

        start.setOnClickListener {
            if(!running) {
                chro.base = SystemClock.elapsedRealtime()-time
                chro.start()
                start.text = "STOP"
                start.setBackgroundColor(Color.RED)
            }
            else{
                chro.stop()
                start.text = "START"
                start.setBackgroundColor(Color.rgb(87, 160, 211))
                time = SystemClock.elapsedRealtime() - chro.base
            }
            running = !running
        }
        reset.setOnClickListener {
            if (running) {
                chro.stop()
                start.text = "START"
                start.setBackgroundColor(Color.rgb(87, 160, 211))
                chro.base = SystemClock.elapsedRealtime()
            }
            else{
                chro.base = SystemClock.elapsedRealtime()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        //calculate display time if currently running
        if(running) {
            time = SystemClock.elapsedRealtime()-chro.base
        }

        outState.putLong(STATE_TIME, time)

        super.onSaveInstanceState(outState)
    }


    private fun wirewidgets() {
        start = findViewById(R.id.button_main_start)
        start.setBackgroundColor(Color.rgb(87, 160, 211))
        reset = findViewById(R.id.button_main_reset)
        reset.setBackgroundColor(Color.rgb(123, 104, 238))
        chro = findViewById(R.id.chronometer_main_stopwatch)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }


}