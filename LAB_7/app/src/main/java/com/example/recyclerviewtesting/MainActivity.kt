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

        val names = arrayOf("Java","Kotlin","Flutter","React Native","Java","Kotlin","Flutter","React Native","Java","Kotlin","Flutter","React Native");
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



class MyAdapter (ctx:Context,namesArr:Array<String>)
    : RecylcerView.Adapter<MyAdapter.MyViewHolder>(){


    val inflaterInstanceObj = inflaterInstance.from(ctx);

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        val nameTextRef = itemView.findViewById<TextView>(R.id.name_tx);
    }

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):MyViewHolder{

        val itemLayoutInst = inflaterInstanceObj.inflate(R.layout.my_item_view_layout, parent , false);

        return MyViewHolder(itemLayoutInst);
    }

    override fun onBindViewHolder(holder:MyViewHolder,position:Int):{
        holder.nameTextRef.text = namesArr[position]
    }

    override fun getItemCount():Int{
        return namesArr.size;
    }

}

// In our Activity on creation.

override fun onCreate(savedInstanceBundle:Bundle?){
    super.onCreate(savedInstanceBundle);
    stContextView(R.layout.my_activity);



    val namesArr = arrayOf("shamaz","Ali","zeeshan","sheri")
    val myRecyclerRef = findViewById(R.id.myRecList);

    myRecyclerRef.adapter = MyAdapter(this,names);
    myRecyclerRef.layoutManager = LinearLyaoutManager.from(this)
}