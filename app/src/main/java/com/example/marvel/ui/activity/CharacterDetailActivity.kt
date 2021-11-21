package com.example.marvel.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import com.example.marvel.MarvelApplication
import com.example.marvel.databinding.ActivityCharacterDetailBinding
import com.example.marvel.ui.fragment.CharacterDetailFragment

class CharacterDetailActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityCharacterDetailBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MarvelApplication().appComponent.inject(this)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, CharacterDetailFragment().apply {
                enterTransition = Slide(Gravity.END)
                exitTransition = Slide(Gravity.START)
            })
            commit()
        }

    }
}