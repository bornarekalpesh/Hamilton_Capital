package com.example.hamiltoncapital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.example.hamiltoncapital.activity.MainActivity
import com.example.hamiltoncapital.databinding.SplashMainBinding

class SplashActivity : AppCompatActivity() {
   lateinit var binding:SplashMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.splash_main)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}