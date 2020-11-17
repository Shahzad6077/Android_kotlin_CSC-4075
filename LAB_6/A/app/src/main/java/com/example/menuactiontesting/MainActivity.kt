package com.example.menuactiontesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var actionMode:ActionMode?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewBtn = findViewById<TextView>(R.id.btn)

        val contextBtn = findViewById<TextView>(R.id.contextBtn)

        textViewBtn.setOnClickListener {
            when(actionMode){
                null->{
                    actionMode=startActionMode(actionCallback)
                    true
                }
                else-> false
            }
        }

        registerForContextMenu(contextBtn);
    }
    val actionCallback = object :ActionMode.Callback{
        override fun onCreateActionMode(p0: ActionMode?, p1: Menu?): Boolean {
            menuInflater.inflate(R.menu.action_menu,p1)
            return true
        }

        override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(p0: ActionMode?, p1: MenuItem?): Boolean {
            when(p1?.itemId){
                R.id.firstItem -> Toast.makeText(applicationContext,"Item 1 is Clicked.",Toast.LENGTH_LONG).show()
                R.id.secondItem -> Toast.makeText(applicationContext,"Item 2 is Clicked.",Toast.LENGTH_LONG).show()
                R.id.thirdItem -> Toast.makeText(applicationContext,"Item 3 is Clicked.",Toast.LENGTH_LONG).show()
            }
            return true
        }

        override fun onDestroyActionMode(p0: ActionMode?) {
            actionMode=null;
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

    }

//    IMPLEMENT CONTEXT MENU FUNCTIONS
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        when (item!!.itemId) {
            R.id.call ->{
                Toast.makeText(applicationContext, "call code", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.sms ->{
                Toast.makeText(applicationContext, "sms code", Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

}