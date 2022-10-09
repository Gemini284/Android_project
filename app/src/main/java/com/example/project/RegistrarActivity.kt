package com.example.project

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.project.databinding.ActivityRegistrarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarBinding
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private var email = ""
    private var password = ""
    private var name = ""
    private var mail = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Sign Up"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Porfavor espere")
        progressDialog.setMessage(" Creando cuenta en...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.SignupBttn.setOnClickListener{
            validateData()
        }

    }

    private fun validateData() {
        name = binding.editTextNameRegister.text.toString()
        email = binding.editTextEmailRegister.text.toString().trim()
        mail = binding.editTextEmailRegister.text.toString().trim()
        password = binding.editTextPasswordRegister.text.toString().trim()
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.editTextEmailRegister.error = "Formato de e-mail invalido"
        }else if(TextUtils.isEmpty(password)){
            binding.editTextPasswordRegister.error = "Ingresa una contraseña"
        }else if (password.length <6){
            binding.editTextPasswordRegister.error = "La contraseña debe tener al menos 6 caracteres"
        }else{
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            progressDialog.dismiss()

            val firebaseUser = firebaseAuth.currentUser
            val email = firebaseUser!!.email
            val uid = firebaseAuth.currentUser?.uid


            databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios")

            if (uid != null) {

                databaseReference.child(uid).child("Nombre").setValue(name)
                databaseReference.child(uid).child("Email").setValue(mail)
            }
            Toast.makeText(this, "Registro con: $email", Toast.LENGTH_SHORT).show()

            //abre el perfil
            startActivity(Intent(this, Activity_Donador::class.java))
            finish()

        }.addOnFailureListener { e ->
            progressDialog.dismiss()
            Toast.makeText(this, "Registro fallado debido a ${e.message}", Toast.LENGTH_SHORT)
                .show()
        }
        binding.bttnInicio.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
