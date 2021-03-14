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

class SignIn : AppCompatActivity() {
    var username: EditText? = null
    var password: EditText? = null
    var btnlogin: Button? = null
    var btnsignup1: Button? = null
    var DB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        username = findViewById<View>(R.id.username1) as EditText
        password = findViewById<View>(R.id.password1) as EditText
        btnlogin = findViewById<View>(R.id.btnsignin1) as Button
        btnsignup1 = findViewById<View>(R.id.btnsignup1) as Button
        DB = DBHelper(this)
        btnlogin!!.setOnClickListener {
            val user = username!!.text.toString()
            val pass = password!!.text.toString()
            if (user == "" || pass == "") Toast.makeText(
                this@SignIn,
                "Please enter all the fields",
                Toast.LENGTH_SHORT
            ).show() else {
                val checkuserpass = DB!!.checkusernamepassword(user, pass)
                if (checkuserpass == true) {
                    val sharedPref = getSharedPreferences(
                        "CHECKER",
                        MODE_PRIVATE
                    )
                    val editor = sharedPref.edit()
                    editor.putString(getString(R.string.LOGIN_AS), "USER")
                    editor.putString("username", user)
                    editor.commit()

                    //                        ImageView deleteBtnRef = (ImageView) findViewById(R.id.deleteBtn);
                    //
                    //                        deleteBtnRef.setVisibility(View.GONE);
                    username!!.setText("")
                    password!!.setText("")
                    Toast.makeText(this@SignIn, "Sign in successfull", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, UserActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignIn, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnsignup1!!.setOnClickListener {
            val intent = Intent(applicationContext, SignUp::class.java)
            startActivity(intent)
        }
        username!!.onFocusChangeListener =
            OnFocusChangeListener { v, b -> hideTheKeyboard(v, b) }
        password!!.onFocusChangeListener =
            OnFocusChangeListener { v, b -> hideTheKeyboard(v, b) }
    }

    private fun hideTheKeyboard(v: View, hasFocused: Boolean) {
        if (!hasFocused) {
            val imm = this@SignIn.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
        }
    }
}