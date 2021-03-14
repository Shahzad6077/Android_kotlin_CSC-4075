package com.example.findtutor

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

class UserActivity : AppCompatActivity() {
    private var userAppBarConfiguration: AppBarConfiguration? = null
    private var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_user)
        setSupportActionBar(toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.user_drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.user_nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        Log.i("CHK", "user-activity")
        userAppBarConfiguration = AppBarConfiguration.Builder(
            R.id.listFragementTutor, R.id.favouriteFragmentTutor, R.id.nav_logout
        )
            .setDrawerLayout(drawer)
            .build()
        val navController = Navigation.findNavController(this, R.id.user_nav_host_frag)
        NavigationUI.setupActionBarWithNavController(this, navController, userAppBarConfiguration!!)
        NavigationUI.setupWithNavController(navigationView, navController)


//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Log.i("NAV","----> "+item.getItemId());
//                return true;
//            }
//        });
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.user, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.user_nav_host_frag)
        return (NavigationUI.navigateUp(navController, userAppBarConfiguration!!)
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
        Toast.makeText(this@UserActivity, "Press back again to Logout", Toast.LENGTH_SHORT).show()
        val h = Handler()
        h.postDelayed({ setPressBackBooleanVal(false) }, 2000)
    }
}