package com.example.marvel.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marvel.MarvelApplication
import com.example.marvel.data.db.AppDatabase
import com.example.marvel.data.model.CharacterModel
import com.example.marvel.data.service.local.LocalDataSourceImpl
import com.example.marvel.databinding.FragmentCharacterDetailBinding
import com.example.marvel.ui.adapter.ComicsRecyclerViewAdapter
import com.example.marvel.ui.viewmodel.ComicViewModel
import com.example.marvel.ui.viewmodel._character
import com.example.marvel.ui.viewmodel.viewModelProvider
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CharacterDetailFragment : Fragment() {
    private var _binding : FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private var startYear : Int = 2005
    private val limit : Int = 10

    private val adapter by lazy { ComicsRecyclerViewAdapter() }

    private val localDataSource by lazy { LocalDataSourceImpl(AppDatabase.getDatabase(requireActivity().application)) }
    private lateinit var characterModel : CharacterModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private fun getViewModel(): ComicViewModel {
        return viewModelProvider(viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MarvelApplication().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.adapter = adapter

        _character?.let {
            val imagePath = "${it.thumbnail.path.replace("http://","https://")}/standard_amazing.${it.thumbnail.extension}"
            Picasso.get().load(imagePath).fit().into(binding.image)
            binding.nameText.text = it.name
            if (it.description != ""){
                binding.descriptionTitle.visibility = VISIBLE
                binding.descriptionText.visibility = VISIBLE
                binding.descriptionText.text = it.description
            }
            else{
                binding.descriptionTitle.visibility = GONE
                binding.descriptionText.visibility = GONE
            }

            characterModel = CharacterModel(it.id, it.name, it.description, imagePath)
            getViewModel().getFavorite(it.id, localDataSource)

            getViewModel().getComics(it.id, startYear, limit, "onsaleDate")
        }

        getViewModel().comicLiveData.observe(requireActivity(), {
            if(it.results.isNotEmpty()) {
                binding.comicText.visibility = VISIBLE
                binding.recyclerview.visibility = VISIBLE
                adapter.addComicList(it.results)
            }
        })

        getViewModel().errorString.observe(requireActivity(), {
            AlertDialog.Builder(requireContext()).setMessage(it).setTitle("Error").setOnCancelListener { dialog->
                dialog.dismiss()
            }
        })

        getViewModel().favoriteLiveData.observe(requireActivity(), {
            binding.favoriteButton.isSelected = it
        })

        binding.favoriteButton.setOnClickListener {
            it.isSelected = !it.isSelected
            if (it.isSelected){
                getViewModel().addFavorite(characterModel, localDataSource)
            }
            else {
                getViewModel().deleteFavorite(characterModel, localDataSource)
            }
        }
    }

    companion object {
        const val TAG = "CharacterDetailFragment"
    }
}