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
        val q2Btn = findViewById<Button>(R.id.q2Btn)
        val q3Btn = findViewById<Button>(R.id.q3Btn)
        val q4Btn = findViewById<Button>(R.id.q4Btn)
        q1Btn.setOnClickListener{
            val intent = Intent(this, question_1::class.java)
            startActivity(intent)
        }
        q2Btn.setOnClickListener{
            val intent = Intent(this, question_2::class.java)
            startActivity(intent)
        }
        q3Btn.setOnClickListener{
            val intent = Intent(this, question_3::class.java)
            startActivity(intent)
        }
        q4Btn.setOnClickListener{
            val intent = Intent(this, question_4::class.java)
            startActivity(intent)
        }
    }
}