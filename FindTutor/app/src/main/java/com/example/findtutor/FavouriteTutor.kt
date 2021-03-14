package com.example.findtutor

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class FavouriteTutor : Fragment() {
    var MyDB: DBHelper? = null
    var arrOfTutors: ArrayList<MyData>? = null
    var recyclerView: RecyclerView? = null
    var searchTutorEditText: EditText? = null
    var loginBy: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arrOfTutors = ArrayList<MyData>()
        Log.i("List", "onCreate")
        MyDB = DBHelper(requireActivity())
        val res = MyDB!!.tutorsList
        if (res.count == 0) {
            Toast.makeText(activity, "No Result Found.", Toast.LENGTH_SHORT).show()
        } else {
            while (res.moveToNext()) {
                arrOfTutors!!.add(
                    MyData(
                        res.getString(0),
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(7)
                    )
                )
            }
        }
        val sharedPref = requireActivity().getSharedPreferences("CHECKER", Context.MODE_PRIVATE)
        loginBy = sharedPref.getString(getString(R.string.LOGIN_AS), "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_favourite_tutor, container, false)
        recyclerView = v.findViewById<View>(R.id.favouriteListRecyclerView) as RecyclerView
        searchEntries(true, "")
        return v
    }

    fun searchEntries(isEmptySearch: Boolean, value: String?) {
        val res = MyDB!!.facouriteTutors()
        arrOfTutors = ArrayList<MyData>()
        if (res.count == 0) {
            Toast.makeText(activity, "No Result Found.", Toast.LENGTH_SHORT).show()
        } else {
            while (res.moveToNext()) {
                arrOfTutors!!.add(
                    MyData(
                        res.getString(0),
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(7)
                    )
                )
            }
        }
        val adapter = TutorRecyclerAdapter(arrOfTutors!!, requireActivity())
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(this.context)
        recyclerView!!.adapter = adapter
        adapter.setOnItemClickListener(object : TutorRecyclerAdapter.OnItemClickListener {
            override fun onContactByClick(type: String?, value: String?) {
                onTriggerContactByBtnClick(type, value)
            }

            override fun onFavouriteTutorClick(tutorId: Int?) {
                val t = "->$tutorId"
                Log.i("on favourite btn Click", t)
                val checkInsertData = MyDB!!.addToFavTutor(tutorId)
                if (checkInsertData == true) {
                    Toast.makeText(activity, "Tutor is added to favourites", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        activity,
                        "Something went wrong please try it later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onDeleteItemClick(tutorId: Int?) {
                val t = "->$tutorId"
                val isDeleted = MyDB!!.deleteTutor(tutorId!!)
                if (isDeleted == true) {
                    Toast.makeText(activity, "Tutor Deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        activity,
                        "Something went wrong please try it later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }

    fun onTriggerContactByBtnClick(type: String?, value: String?) {
        var intent = Intent()
        if (type == "PHONE") {
            val number = Uri.parse("tel:$value")
            intent = Intent(Intent.ACTION_DIAL, number)
        }
        if (type == "EMAIL") {
            intent = Intent(Intent.ACTION_SEND)
            // The intent does not have a URI, so declare the "text/plain" MIME type
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(value)) // recipients
            intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject")
            intent.putExtra(Intent.EXTRA_TEXT, "Email message text")
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"))
            // You can also attach multiple items by passing an ArrayList of Uris
        }

        // Verify it resolves
        val packageManager = requireContext().packageManager
        val activities = packageManager.queryIntentActivities(intent, 0)
        val isIntentSafe = activities.size > 0

        // Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(param1: String?, param2: String?): FavouriteTutor {
            val fragment = FavouriteTutor()
            val args = Bundle()
            return fragment
        }
    }
}