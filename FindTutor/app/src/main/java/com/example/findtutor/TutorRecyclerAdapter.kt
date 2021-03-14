package com.example.findtutor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class TutorRecyclerAdapter(private val arrayOfData: ArrayList<MyData>, var context: Context) :
    RecyclerView.Adapter<TutorRecyclerAdapter.EntryViewHolder>() {
    private var mListener: OnItemClickListener? = null
    private val loginBy: String? = null

    interface OnItemClickListener {
        fun onContactByClick(type: String?, value: String?)
        fun onFavouriteTutorClick(tutorId: Int?)
        fun onDeleteItemClick(tutorId: Int?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    inner class EntryViewHolder(itemView: View, listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {
        var nameVT: TextView
        var courseVT: TextView
        var courseCodeVT: TextView
        var qualificationVT: TextView
        var cityVT: TextView
        var actionBtnsInfoVT: TextView
        var emailBtn: Button
        var phoneBtn: Button
        var deleteBtn: ImageView
        var favTutorBtn: ImageView
        var itemPosition = 0
        var ID: String? = null
        var rowData: MyData? = null

        init {
            nameVT = itemView.findViewById<View>(R.id.nameTutorListItemText) as TextView
            courseVT = itemView.findViewById<View>(R.id.courseTutorListItemText) as TextView
            courseCodeVT = itemView.findViewById<View>(R.id.courseCodeTutorListItemText) as TextView
            qualificationVT =
                itemView.findViewById<View>(R.id.qualificationTutorListItemText) as TextView
            cityVT = itemView.findViewById<View>(R.id.cityTutorListItemText) as TextView
            emailBtn = itemView.findViewById<View>(R.id.contactByEmailBtn) as Button
            phoneBtn = itemView.findViewById<View>(R.id.contactByPhoneBtn) as Button
            deleteBtn = itemView.findViewById<View>(R.id.deleteBtn) as ImageView
            favTutorBtn = itemView.findViewById<View>(R.id.favBtnTutor) as ImageView
            actionBtnsInfoVT = itemView.findViewById<View>(R.id.actionBtnsInfo) as TextView
            deleteBtn.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        arrayOfData.removeAt(position)
                        notifyItemRemoved(position)
                        listener.onDeleteItemClick(rowData!!.id.toInt())
                    }
                }
            }
            favTutorBtn.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onFavouriteTutorClick(rowData!!.id.toInt())
                    }
                }
            }
            phoneBtn.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onContactByClick("PHONE", rowData!!.phoneNo)
                    }
                }
            }
            emailBtn.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onContactByClick("EMAIL", rowData!!.email)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View
        listItem = layoutInflater.inflate(R.layout.tutor_list_item, parent, false)
        return EntryViewHolder(listItem, mListener)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.ID = arrayOfData[position].id
        holder.itemPosition = position
        holder.rowData = arrayOfData[position]
        holder.nameVT.text = arrayOfData[position].name
        holder.courseVT.text = arrayOfData[position].course
        holder.courseCodeVT.text = arrayOfData[position].courseCode
        holder.qualificationVT.text = arrayOfData[position].qualification
        holder.cityVT.text = arrayOfData[position].city
        val sharedPref = context.getSharedPreferences("CHECKER", Context.MODE_PRIVATE)
        val loginByChecker = sharedPref.getString(context.getString(R.string.LOGIN_AS), "")
        if (loginByChecker == "ADMIN") {
            holder.deleteBtn.visibility = View.VISIBLE
            holder.favTutorBtn.visibility = View.GONE
            holder.actionBtnsInfoVT.text = "Click to Delete"
        } else if (loginByChecker == "USER") {
            holder.deleteBtn.visibility = View.GONE
            holder.favTutorBtn.visibility = View.VISIBLE
            holder.actionBtnsInfoVT.text = "Click to Fav"
        }
    }

    override fun getItemCount(): Int {
        return arrayOfData.size
    }
}