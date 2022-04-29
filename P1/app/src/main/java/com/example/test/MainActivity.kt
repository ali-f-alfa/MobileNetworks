package com.example.test

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.test.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.editTextTextEmailAddress2.setOnFocusChangeListener(View.OnFocusChangeListener{view, b ->
            if (b){
                // do something when edit text get focus
            }
            else{
                // do something when edit text lost focus
                if(!isValidEmail(binding.editTextTextEmailAddress2.text.toString())){
                    binding.editTextTextEmailAddress2.error = "Email is not valid!"
                }
            }
        })

        binding.facebookBtn.setOnClickListener {
            binding.facebookBtn.setBackgroundColor(getRandomColor())
        }

        binding.GoogleBtn.setOnClickListener {
            binding.GoogleBtn.setBackgroundColor(getRandomColor())
        }

        binding.LoginBtn.setOnClickListener {
            Toast.makeText(applicationContext,"Login successful",Toast.LENGTH_SHORT).show()
        }

        binding.GoToSignUpBtn.setOnClickListener{
//            Toast.makeText(applicationContext, "Go to SingUp page",Toast.LENGTH_LONG).show()
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}

fun isValidEmail(input: String): Boolean{
    return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()
}

fun isValidPhone(input: String): Boolean{
    var regstr = "^(\\+98|0)?9\\d{9}\$"
    return input == regstr.toRegex().find(input)?.value

}