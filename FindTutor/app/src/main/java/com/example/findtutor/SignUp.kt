package com.example.findtutor

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {
    var username: EditText? = null
    var password: EditText? = null
    var repassword: EditText? = null
    var signup: Button? = null
    var signin: Button? = null
    var DB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        username = findViewById<View>(R.id.username) as EditText
        password = findViewById<View>(R.id.password) as EditText
        repassword = findViewById<View>(R.id.repassword) as EditText
        signup = findViewById<View>(R.id.btnsignup) as Button
        signin = findViewById<View>(R.id.btnsignin) as Button
        DB = DBHelper(this)
        signup!!.setOnClickListener {
            val user = username!!.text.toString()
            val pass = password!!.text.toString()
            val repass = repassword!!.text.toString()
            if (user == "" || pass == "" || repass == "") Toast.makeText(
                this@SignUp,
                "Please enter all the fields",
                Toast.LENGTH_SHORT
            ).show() else {
                if (pass == repass) {
                    val checkuser = DB!!.checkusername(user)
                    if (checkuser == false) {
                        val insert = DB!!.insertData(user, pass)
                        if (insert == true) {
                            Toast.makeText(
                                this@SignUp,
                                "Registered successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(applicationContext, SignIn::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@SignUp, "Registration failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(
                            this@SignUp,
                            "User already exists! please sign in",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this@SignUp, "Passwords not matching", Toast.LENGTH_SHORT).show()
                }
            }
        }
        signin!!.setOnClickListener {
            val intent = Intent(applicationContext, SignIn::class.java)
            startActivity(intent)
        }
        username!!.onFocusChangeListener =
            OnFocusChangeListener { v, b -> hideTheKeyboard(v, b) }
        password!!.onFocusChangeListener =
            OnFocusChangeListener { v, b -> hideTheKeyboard(v, b) }
        repassword!!.onFocusChangeListener =
            OnFocusChangeListener { v, b -> hideTheKeyboard(v, b) }
    }

    private fun hideTheKeyboard(v: View, hasFocused: Boolean) {
        if (!hasFocused) {
            val imm = this@SignUp.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
        }
    }
}