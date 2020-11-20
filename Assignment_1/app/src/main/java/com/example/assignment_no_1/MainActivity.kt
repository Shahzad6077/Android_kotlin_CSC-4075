package com.example.assignment_no_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val q1Btn = findViewById<Button>(R.id.q1Btn)
        q1Btn.setOnClickListener{
            val intent = Intent(this, question_1::class.java)
            startActivity(intent)
        }
    }
}