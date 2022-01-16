package com.example.game.Fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.game.MainActivity
import com.example.game.R
import com.google.firebase.auth.FirebaseAuth

class FragmentsLogin : Fragment(R.layout.fragments_login) {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var register: Button
    private lateinit var passwordReset: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = view.findViewById(R.id.email)
        password = view.findViewById(R.id.password)
        login = view.findViewById(R.id.login)
        register = view.findViewById(R.id.register)
        passwordReset = view.findViewById(R.id.passwordReset)

        val navController = Navigation.findNavController(view)

        if (FirebaseAuth.getInstance().currentUser != null) {
            val action = FragmentsLoginDirections.actionFragmentsLoginToFragmentsProfile()
            navController.navigate(action)
            Toast.makeText(activity, "Hello", Toast.LENGTH_SHORT).show()
        }

        register.setOnClickListener() {
            val action = FragmentsLoginDirections.actionFragmentsLoginToFragmentsRegister()
            navController.navigate(action)
            Toast.makeText(activity,"Registration",Toast.LENGTH_SHORT).show()
        }

        passwordReset.setOnClickListener() {
            val action = FragmentsLoginDirections.actionFragmentsLoginToFragmentsPasswordReset()
            navController.navigate(action)
            Toast.makeText(activity,"Password reset",Toast.LENGTH_SHORT).show()
        }

        login.setOnClickListener() {

            val email = email.text.toString()
            val password = password.text.toString()

            if (!email.contains("@")) {
                Toast.makeText(activity, "Enter the  E-mail !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 9 || !password.contains(Regex("[0-9]"))) {
                Toast.makeText(activity, "Enter the Password !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(activity, "Successfully login", Toast.LENGTH_SHORT).show()
                            val passwordLog = password
                            val action =
                                FragmentsLoginDirections.actionFragmentsLoginToFragmentsProfile(passwordLog)
                            navController.navigate(action)
                        }else {
                            Toast.makeText(activity, "Error ! Try again !", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}

