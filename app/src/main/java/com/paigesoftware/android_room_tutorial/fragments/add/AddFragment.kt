package com.paigesoftware.android_room_tutorial.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.paigesoftware.android_room_tutorial.R
import com.paigesoftware.android_room_tutorial.data.User
import com.paigesoftware.android_room_tutorial.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    /** DB Related **/
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        /** DB Related **/
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    /** CRATE **/
    private fun insertDataToDatabase() {
        val firstName = etFirastName.text.toString()
        val lastName = etLastName.text.toString()
        val age = etAge.text
        if(inputCheck(firstName, lastName, age)) {
            // Create User Object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            // Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}