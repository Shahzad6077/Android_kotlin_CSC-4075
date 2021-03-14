package com.example.findtutor

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginAdmin : AppCompatActivity() {
    var loginBtn: Button? = null
    var pwField: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_admin)
        loginBtn = findViewById<View>(R.id.loginAdminBtn) as Button
        loginBtn!!.setOnClickListener {
            pwField = findViewById<View>(R.id.adminPwField) as EditText
            val pass = pwField!!.text.toString()
            if (pass == "asdasd") {
                val sharedPref = getSharedPreferences(
                    "CHECKER",
                    MODE_PRIVATE
                )
                val editor = sharedPref.edit()
                editor.putString(getString(R.string.LOGIN_AS), "ADMIN")
                editor.commit()

                //                    ImageView deleteBtnRef = (ImageView) findViewById(R.id.deleteBtn);
                //
                //                    deleteBtnRef.setVisibility(View.VISIBLE);
                pwField!!.setText("")
                Toast.makeText(this@LoginAdmin, "Successfully Login", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@LoginAdmin, "Enter Correct Password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}