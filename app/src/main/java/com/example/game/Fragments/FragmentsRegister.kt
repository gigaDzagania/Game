package com.example.game.Fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.game.R
import com.google.firebase.auth.FirebaseAuth

class FragmentsRegister : Fragment(R.layout.fragments_register) {

    private lateinit var name: EditText
    private lateinit var surname: EditText
    private lateinit var registrationEmail: EditText
    private lateinit var registrationPassword1: EditText
    private lateinit var registrationPassword2: EditText
    private lateinit var checkboxMale: CheckBox
    private lateinit var checkboxFemale: CheckBox
    private lateinit var regRegistration: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = view.findViewById(R.id.name)
        surname = view.findViewById(R.id.surname)
        registrationEmail = view.findViewById(R.id.registrationEmail)
        registrationPassword1 = view.findViewById(R.id.registrationPassword1)
        registrationPassword2 = view.findViewById(R.id.registrationPassword2)
        checkboxMale = view.findViewById(R.id.checkboxMale)
        checkboxFemale = view.findViewById(R.id.checkboxFemale)
        regRegistration = view.findViewById(R.id.regRegistration)

        val navController = Navigation.findNavController(view)

        regRegistration.setOnClickListener() {

            val email = registrationEmail.text.toString()
            val password = registrationPassword1.text.toString()

            if (name.text.isEmpty() || surname.text.isEmpty()) {
                Toast.makeText(activity, "Enter name  ( surname ) !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!email.contains("@")) {
                Toast.makeText(activity, "Enter the  E-mail !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 9 || !password.contains(Regex("[0-9]"))) {
                Toast.makeText(activity, "Enter the Password !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!registrationPassword2.text.contains(password)) {
                Toast.makeText(activity, "Repeat the password correctly !", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if ((!checkboxMale.isChecked && !checkboxFemale.isChecked)
                || (checkboxMale.isChecked && checkboxFemale.isChecked)
            ) {
                Toast.makeText(activity, "Checked only one correctly!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(activity, "Successful registration !", Toast.LENGTH_SHORT).show()
                            val action =
                                FragmentsRegisterDirections.actionFragmentsRegisterToFragmentsProfile()
                            navController.navigate(action)
                        } else {
                            Toast.makeText(activity, "Error ! Try again !", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}
