package com.example.marvel.ui.activity

import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.marvel.MarvelApplication
import com.example.marvel.R
import com.example.marvel.databinding.ActivityCharacterListBinding
import com.example.marvel.ui.fragment.CharacterListFragment
import com.example.marvel.ui.fragment.FavoriteListFragment
import com.example.marvel.ui.unit.FragmentListener

class CharacterListActivity : AppCompatActivity(), FragmentListener {

    private val binding by lazy { ActivityCharacterListBinding.inflate(layoutInflater) }

    private val favoriteListFragment = FavoriteListFragment().apply {
        enterTransition = Slide(Gravity.END)
        exitTransition = Slide(Gravity.START)
    }
    private val characterListFragment = CharacterListFragment(this, favoriteListFragment).apply {
        enterTransition = Slide(Gravity.END)
        exitTransition = Slide(Gravity.START)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MarvelApplication().appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setFragment(characterListFragment, null)
    }

    private fun setFragment(fragment : Fragment, tag: String?){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, fragment)

            if (tag != null){
                addToBackStack(tag)
            }
            commit()
        }
    }

    override fun openFragment(fragment: Fragment, tag: String) {
        setFragment(fragment, tag)
    }
}