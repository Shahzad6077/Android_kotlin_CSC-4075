package com.example.recyclerviewtesting

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val names = arrayOf("Shahzad","Ali","Fahad","Zeeshan","Sheri","Shahzad","Ali","Fahad","Zeeshan","Sheri","Shahzad","Ali","Fahad","Zeeshan","Sheri");
        val numbers = arrayOf("1","2","3","4","5","1","2","3","4","5","1","2","3","4","5")

        val recyclerViewObj = findViewById<RecyclerView>(R.id.contactList);

        recyclerViewObj.adapter = ContactRecyclerAdapter(this,names,numbers);
        recyclerViewObj.layoutManager = LinearLayoutManager(this)

    }
}

class  ContactRecyclerAdapter(ctx:Context, names:Array<String>,numbers:Array<String>):
        RecyclerView.Adapter<ContactRecyclerAdapter.ContactViewHolder>() {


    val namesList = names;
    val numbersList = numbers;
    val inflaterInstance = LayoutInflater.from(ctx);

    class ContactViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val numberTv = itemView.findViewById<TextView>(R.id.num);
        val nameTv = itemView.findViewById<TextView>(R.id.name);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val rvView = inflaterInstance.inflate(R.layout.contact_recycler_view,parent,false);
        return ContactViewHolder(rvView);
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
       holder.nameTv.text = namesList[position];
        holder.numberTv.text = numbersList[position];
    }

    override fun getItemCount(): Int {
        return namesList.size;
    }


}