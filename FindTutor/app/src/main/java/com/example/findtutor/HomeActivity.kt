package com.example.findtutor

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    private var mAppBarConfiguration: AppBarConfiguration? = null
    private var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_add_tutor_item, R.id.nav_view_tutor_item, R.id.nav_logout
        )
            .setDrawerLayout(drawer)
            .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration!!)
        NavigationUI.setupWithNavController(navigationView, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return (NavigationUI.navigateUp(navController, mAppBarConfiguration!!)
                || super.onSupportNavigateUp())
    }

    fun setPressBackBooleanVal(v: Boolean) {
        doubleBackToExitPressedOnce = v
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce == true) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this@HomeActivity, "Press back again to Logout", Toast.LENGTH_SHORT).show()
        val h = Handler()
        h.postDelayed({ setPressBackBooleanVal(false) }, 2000)
    }
}