package com.example.game.Fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.game.R
import com.google.firebase.auth.FirebaseAuth

class FragmentsProfile : Fragment(R.layout.fragments_profile) {

    private lateinit var name1: EditText
    private lateinit var name2: EditText
    private lateinit var btnStart: Button
    private lateinit var passwordChange: Button
    private lateinit var logOut: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name1 = view.findViewById(R.id.name1)
        name2 = view.findViewById(R.id.name2)
        btnStart = view.findViewById(R.id.btnStart)
        passwordChange = view.findViewById(R.id.passwordChange)
        logOut = view.findViewById(R.id.logOut)

        val passwordLog = FragmentsProfileArgs.fromBundle(requireArguments()).passwordLog

        val navController = Navigation.findNavController(view)

        logOut.setOnClickListener() {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(activity, "Sign out", Toast.LENGTH_SHORT).show()
            val action = FragmentsProfileDirections.actionFragmentsProfileToFragmentsLogin()
            navController.navigate(action)
        }

        passwordChange.setOnClickListener() {
            val action = FragmentsProfileDirections.actionFragmentsProfileToFragmentsPasswordChange(passwordLog)
            navController.navigate(action)
            Toast.makeText(activity, "Password change", Toast.LENGTH_SHORT).show()
        }

        btnStart.setOnClickListener() {
            val name1 = name1.text.toString()
            val name2 = name2.text.toString()

            val action = FragmentsProfileDirections.actionFragmentsProfileToFragmentsGame(name1,name2)
            navController.navigate(action)
            Toast.makeText(activity, "Game Star", Toast.LENGTH_SHORT).show()
        }
    }
}
