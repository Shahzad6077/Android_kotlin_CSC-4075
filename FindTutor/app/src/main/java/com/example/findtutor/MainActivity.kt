package com.example.findtutor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var btnadmin: Button? = null
    var btnuser: Button? = null
    private var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnadmin = findViewById<View>(R.id.btnadmin) as Button
        btnuser = findViewById<View>(R.id.btnuser) as Button
        btnuser!!.setOnClickListener {
            val intent = Intent(applicationContext, SignIn::class.java)
            startActivity(intent)
        }
        btnadmin!!.setOnClickListener {
            val intent = Intent(applicationContext, LoginAdmin::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce == true) {
            finishAffinity()
            System.exit(0)
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this@MainActivity, "Press back again to Exit the App.", Toast.LENGTH_SHORT)
            .show()
        val h = Handler()
        h.postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}