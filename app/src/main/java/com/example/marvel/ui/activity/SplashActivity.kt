package com.example.marvel.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.marvel.MarvelApplication
import com.example.marvel.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        MarvelApplication().appComponent.inject(this)

        object : CountDownTimer(3000, 3000){
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                Intent(this@SplashActivity, CharacterListActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(this)
                }
            }
        }.start()
    }
}