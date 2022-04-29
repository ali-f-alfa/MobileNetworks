package com.example.test

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.databinding.ActivitySignUpBinding
import java.util.*

class SignUp : AppCompatActivity() {
    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SignUpEmail.setOnFocusChangeListener(View.OnFocusChangeListener{ view, b ->
            if (b){
                // do something when edit text get focus
            }
            else{
                // do something when edit text lost focus
                if(!isValidEmail(binding.SignUpEmail.text.toString())){
                    binding.SignUpEmail.error = "Email is not valid !"
                }
            }
        })

        binding.phoneSignUp.setOnFocusChangeListener(View.OnFocusChangeListener{ view, b ->
            if (b){
                // do something when edit text get focus
            }
            else{
                // do something when edit text lost focus
                if(!isValidPhone(binding.phoneSignUp.text.toString())){
                    binding.phoneSignUp.error = "Phone number is not valid !"
                }
            }
        })
        binding.CreateBtn.setOnClickListener {
            Toast.makeText(applicationContext,"User created", Toast.LENGTH_SHORT).show()
        }

        binding.backBtn.setOnClickListener {
            super.onBackPressed()
        }
        binding.GotoLoginBtn.setOnClickListener {
            super.onBackPressed()
        }
    }

}
