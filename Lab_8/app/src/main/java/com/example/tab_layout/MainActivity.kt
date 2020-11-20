package com.example.tab_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayoutObj = findViewById<TabLayout>(R.id.tabLayout);
        val viewPagerObj = findViewById<ViewPager>(R.id.view);

        viewPagerObj.adapter = MyTabAdapter(supportFragmentManager);

        tabLayoutObj.setupWithViewPager(viewPagerObj);

    }

    override fun onBackPressed() {
        if(doubleBackToExitPressedOnce){
            super.onBackPressed()
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,"Press back again to exit", Toast.LENGTH_SHORT).show();

        Handler().postDelayed({doubleBackToExitPressedOnce=false},2000);

    }
}

class MyTabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    val titles = arrayOf("One","Two","Three");

    override fun getCount(): Int {
        return titles.size;
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> One()
            1 -> Two()
            else -> Three()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position];
    }
}