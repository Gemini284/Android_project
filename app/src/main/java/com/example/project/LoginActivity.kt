package com.example.project

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
        private lateinit var firebaseAuth: FirebaseAuth
        private lateinit var binding: ActivityLoginBinding
        private lateinit var actionBar: ActionBar
        private lateinit var progressDialog: ProgressDialog
        private lateinit var databaseReference: DatabaseReference
        private var email = ""
        private var password = ""
        private var isOrg = false

        fun checkUser() {
            databaseReference = Firebase.database.getReference("Usuarios")
            val firebaseUser = firebaseAuth.currentUser
            val uid = firebaseUser?.uid

            if (uid != null) {
                databaseReference.child(uid).child("isOrg").get().addOnSuccessListener {
                    isOrg = it.value as Boolean
                }.addOnFailureListener{
                    Log.e("firebase", "Error getting data", it)
                }
            }

            if(firebaseUser != null && isOrg == false){
                Toast.makeText(this,"NO ES ORGANIZACION", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Activity_Donador::class.java)) //Cambiar cuando tenga la clase del usuario
                finish()
            } else {
                Toast.makeText(this,"SI ES ORGANIZACION", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Activity_Organizacion::class.java)) //Cambiar cuando tenga la clase del usuario
                finish()
            }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)


            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)
            var mAuth = FirebaseAuth.getInstance()
            actionBar = supportActionBar!!
            actionBar.title = "Login"

            progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Porfavor, espere")
            progressDialog.setMessage("Haciendo login...")
            progressDialog.setCanceledOnTouchOutside(false)

            firebaseAuth = FirebaseAuth.getInstance()
            checkUser()

            binding.textViewRegisterNow.setOnClickListener {
                startActivity(Intent(this, RegistrarActivity::class.java))
            }
            binding.Inicio.setOnClickListener{
                //antes del login, valida
                validateData()
            }
        }
        private fun validateData(){
            email = binding.editTextEmail.text.toString().trim()
            password = binding.editTextPassword.text.toString()

            //a validar
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                //no coincide el email
                binding.editTextEmail.error = "Correo inválido"
            }else if(TextUtils.isEmpty(password)){
                binding.editTextPassword.error = "Ingresa contraseña"
            }else{
                firebaseLogin()
            }
        }

        private fun firebaseLogin() {
            progressDialog.show()
            firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener {
                    progressDialog.dismiss()
                    val firebaseUser = firebaseAuth.currentUser
                    val email = firebaseUser!!.email
                    Toast.makeText(this,"Login con: $email", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,Activity_Donador::class.java))
                    finish()
                }
                .addOnFailureListener { e->
                    progressDialog.dismiss()
                    Toast.makeText(this,"Login fallo debido a: ${e.message}", Toast.LENGTH_SHORT).show()

                }
        }

    }


