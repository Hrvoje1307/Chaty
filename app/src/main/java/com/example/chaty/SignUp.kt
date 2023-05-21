package com.example.chaty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class SignUp : AppCompatActivity() {

    private lateinit var edtName:EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var btnLogin: Button

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth=FirebaseAuth.getInstance()


        edtName=findViewById(R.id.edt_name)
        edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        btnSignUp=findViewById(R.id.btn_signUp)
        btnLogin=findViewById(R.id.btn_logIn)

        btnLogin.setOnClickListener {
            val intent= Intent(this,LogIn::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {
            val email = edtEmail.text.toString()
            val password=edtPassword.text.toString()

            signup(email,password)
        }
    }

    private fun signup(email:String,password:String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   //code for jump to home
                    val intent=Intent(this@SignUp,MainActivity::class.java)
                    startActivity(intent)
                } else {
                   Toast.makeText(this@SignUp,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }
    }
}