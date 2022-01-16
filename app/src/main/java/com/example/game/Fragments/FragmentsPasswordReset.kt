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

class FragmentsPasswordReset : Fragment(R.layout.fragments_password_reset) {

    private lateinit var sendEmail: EditText
    private lateinit var btnSend: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendEmail = view.findViewById(R.id.sendEmail)
        btnSend = view.findViewById(R.id.btnSend)

        val navController = Navigation.findNavController(view)

        btnSend.setOnClickListener() {

            val email = sendEmail.text.toString()

            if (!email.contains("@")) {
                Toast.makeText(activity, "Enter the  E-mail !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(activity, "Check E-mail", Toast.LENGTH_SHORT).show()
                        val action = FragmentsPasswordResetDirections.actionFragmentsPasswordResetToFragmentsLogin()
                        navController.navigate(action)
                    }else {
                        Toast.makeText(activity, "Error ! Try again !", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
