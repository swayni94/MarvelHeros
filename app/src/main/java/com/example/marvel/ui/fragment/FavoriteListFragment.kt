package com.example.marvel.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marvel.MarvelApplication
import com.example.marvel.data.db.AppDatabase
import com.example.marvel.data.service.local.LocalDataSourceImpl
import com.example.marvel.databinding.FragmentFavoriteListBinding
import com.example.marvel.ui.adapter.FavoriteRecyclerViewAdapter
import com.example.marvel.ui.viewmodel.FavoriteListViewModel
import com.example.marvel.ui.viewmodel.viewModelProvider
import javax.inject.Inject


class FavoriteListFragment : Fragment() {
    private var _binding : FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!


    private val localDataSource by lazy { LocalDataSourceImpl(AppDatabase.getDatabase(requireActivity().application)) }
    private val adapter by lazy { FavoriteRecyclerViewAdapter() }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): FavoriteListViewModel {
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
        _binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.adapter = adapter

        getViewModel().getFavoriteList(localDataSource)

        getViewModel().characterInfoLiveData.observe(requireActivity(), {
            adapter.addList(it)
        })

        getViewModel().errorString.observe(requireActivity(), {
            AlertDialog.Builder(requireContext()).setMessage(it).setTitle("Error").setOnCancelListener { dialog->
                dialog.dismiss()
            }
        })
    }

    companion object {
        const val TAG = "FavoriteListFragment"
    }
}