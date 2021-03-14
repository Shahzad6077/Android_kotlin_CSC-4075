package com.example.findtutor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment

class AddTutor : Fragment() {
    var name: EditText? = null
    var course: EditText? = null
    var courseCode: EditText? = null
    var email: EditText? = null
    var phoneNo: EditText? = null
    var city: EditText? = null
    var MyDB: DBHelper? = null
    var qualification: Spinner? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyDB = DBHelper(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_tutor, container, false)
        val qSpinner = view.findViewById<View>(R.id.qualificationSpinner) as Spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.qualification_array,
            android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        qSpinner.adapter = adapter


        // Get and Set all input fields.
        name = view.findViewById(R.id.nameEditText)
        course = view.findViewById(R.id.courseEditText)
        courseCode = view.findViewById(R.id.courseCodeEditText)
        email = view.findViewById(R.id.emailEditText)
        phoneNo = view.findViewById(R.id.phoneNoEditText)
        city = view.findViewById(R.id.cityEditText)
        qualification = view.findViewById(R.id.qualificationSpinner)
        setFocusChangeListenersForInputFields(view)
        InsertBtnClickListener(view)
        return view
    }

    private fun InsertBtnClickListener(v: View) {
        val insertBtn = v.findViewById<Button>(R.id.insertBtn)
        insertBtn.setOnClickListener {
            val nameTxt = name!!.text.toString()
            val courseTxt = course!!.text.toString()
            val courseCodeTxt = courseCode!!.text.toString()
            val emailTxt = email!!.text.toString()
            val phoneNoTxt = phoneNo!!.text.toString()
            val cityTxt = city!!.text.toString()
            val qualificationTxt = qualification!!.selectedItem.toString()
            if (qualification!!.selectedItemPosition == 0 || nameTxt == "" || courseTxt == "" || courseCodeTxt == "" || emailTxt == "" || phoneNoTxt == "" || cityTxt == "") {
                Toast.makeText(activity, "Please fill all fields.", Toast.LENGTH_SHORT).show()
            } else {
                val checkInsertData = MyDB!!.insertTutorEntry(
                    nameTxt,
                    courseTxt,
                    courseCodeTxt,
                    qualificationTxt,
                    emailTxt,
                    phoneNoTxt,
                    cityTxt
                )
                if (checkInsertData == true) {
                    Toast.makeText(activity, "Tutor is Added Successfully.", Toast.LENGTH_SHORT)
                        .show()
                    name!!.setText("")
                    course!!.setText("")
                    courseCode!!.setText("")
                    email!!.setText("")
                    phoneNo!!.setText("")
                    city!!.setText("")
                    qualification!!.setSelection(0)
                } else {
                    Toast.makeText(activity, "Not Added.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setFocusChangeListenersForInputFields(view: View) {
        name!!.onFocusChangeListener =
            OnFocusChangeListener { v, b -> hideTheKeyboard(view, b) }
        course!!.onFocusChangeListener =
            OnFocusChangeListener { v, b -> hideTheKeyboard(view, b) }
        courseCode!!.onFocusChangeListener =
            OnFocusChangeListener { v, b -> hideTheKeyboard(view, b) }
        email!!.onFocusChangeListener =
            OnFocusChangeListener { v, b -> hideTheKeyboard(view, b) }
        phoneNo!!.onFocusChangeListener =
            OnFocusChangeListener { v, b -> hideTheKeyboard(view, b) }
        city!!.onFocusChangeListener =
            OnFocusChangeListener { v, b -> hideTheKeyboard(view, b) }
    }

    private fun hideTheKeyboard(v: View, hasFocused: Boolean) {
        if (!hasFocused) {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    companion object {
        fun newInstance(param1: String?, param2: String?): AddTutor {
            val fragment = AddTutor()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}