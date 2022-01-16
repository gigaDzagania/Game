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

class FragmentsPasswordChange : Fragment(R.layout.fragments_password_change) {

    private lateinit var oldPassword: EditText
    private lateinit var newPassword1: EditText
    private lateinit var newPassword2: EditText
    private lateinit var changePassword: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        oldPassword = view.findViewById(R.id.oldPassword)
        newPassword1 = view.findViewById(R.id.newPassword1)
        newPassword2 = view.findViewById(R.id.newPassword2)
        changePassword = view.findViewById(R.id.changePassword)

        val navController = Navigation.findNavController(view)

        val passwordLog = FragmentsPasswordChangeArgs.fromBundle(requireArguments()).passwordLog

        changePassword.setOnClickListener() {

            val newPassword = newPassword1.text.toString()

            if (!oldPassword.text.contains(passwordLog)) {
                Toast.makeText(activity, "existing password is invalid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (newPassword1.text.length < 9 || !newPassword1.text.contains(Regex("[0-9]"))) {
                Toast.makeText(activity, "Enter Password !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!newPassword2.text.contains(newPassword1.text)
                && newPassword2.text.length != newPassword1.text.length) {
                Toast.makeText(activity, "Repeat Password correct !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                FirebaseAuth.getInstance()
                    .currentUser?.updatePassword(newPassword)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(activity, "Successful password changed !", Toast.LENGTH_SHORT).show()
                            val action = FragmentsPasswordChangeDirections
                                .actionFragmentsPasswordChangeToFragmentsProfile()
                            navController.navigate(action)
                        }
                        else {
                            Toast.makeText(activity, "Error ! Try again !", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}
