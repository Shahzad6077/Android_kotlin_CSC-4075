package com.example.findtutor

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SearchTutor : Fragment() {
    private var loginBy: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences("CHECKER", Context.MODE_PRIVATE)
        loginBy = sharedPref.getString(getString(R.string.LOGIN_AS), "USER")
        Log.i("TOKEN", loginBy!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_tutor, container, false)
    }

    companion object {
        fun newInstance(param1: String?, param2: String?): SearchTutor {
            val fragment = SearchTutor()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}