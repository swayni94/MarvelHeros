package com.example.marvel.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.MarvelApplication
import com.example.marvel.data.model.Character
import com.example.marvel.databinding.FragmentCharacterListBinding
import com.example.marvel.ui.activity.CharacterDetailActivity
import com.example.marvel.ui.adapter.CharactersRecyclerviewAdapter
import com.example.marvel.ui.unit.FragmentListener
import com.example.marvel.ui.viewmodel.CharacterViewModel
import com.example.marvel.ui.viewmodel._character
import com.example.marvel.ui.viewmodel.favoriteMutableLiveData
import com.example.marvel.ui.viewmodel.viewModelProvider
import javax.inject.Inject


class CharacterListFragment (private val listener: FragmentListener, private val fragment: Fragment) : Fragment(), CharactersRecyclerviewAdapter.CharacterListener {
    private var _binding : FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { CharactersRecyclerviewAdapter(this) }

    private var offset : Int = 0
    private var limit : Int = 30

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): CharacterViewModel {
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
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.adapter = adapter

        getViewModel().getCharacters(offset, limit)

        getViewModel().characterInfoLiveData.observe(requireActivity(), {
            adapter.addList(it.results)
            binding.progressBar.visibility = GONE
        })

        getViewModel().errorString.observe(requireActivity(), {
            AlertDialog.Builder(requireContext()).setMessage(it).setTitle("Error").setOnCancelListener { dialog->
                dialog.dismiss()
            }
        })

        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
                    if (lastItem == adapter.getLastItem()){
                        binding.progressBar.visibility = VISIBLE
                        offset++
                        getViewModel().getCharacters(offset, limit)
                    }
                }
            }
        })

        binding.favoriteButton.setOnClickListener {
            listener.openFragment(fragment, TAG)
        }
    }

    companion object {
        const val TAG = "CharacterListFragment"
    }

    override fun openCharacterDetailPage(character: Character) {
        _character = character
        Intent(requireContext(), CharacterDetailActivity::class.java).apply {
            startActivity(this)
        }
    }
}