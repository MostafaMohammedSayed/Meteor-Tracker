package com.example.android.meteortracker.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.meteortracker.R
import com.example.android.meteortracker.bussinessdata.Meteor
import com.example.android.meteortracker.databinding.FragmentMeteorsBinding
import com.example.android.meteortracker.databinding.FragmentMeteorsEgyBinding


class EgyMeteorsFragment : Fragment() {

    private lateinit var binding: FragmentMeteorsEgyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMeteorsEgyBinding.inflate(inflater)

        val viewModel = ViewModelProvider(
            this,MeteorViewModel.Factory(requireActivity().application))
            .get(MeteorViewModel::class.java)

        binding.viewModel = viewModel


        val adapter = MeteorAdapter(MeteorListener {
            viewModel.onMeteorClicked(it)
        })

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                EgyMeteorsFragmentDirections.actionEgyMeteorsFragmentToDetailFragment(it))

                viewModel.onNavigationCompleted() }
        })

        binding.egyMeteorRecycler.adapter = adapter


        viewModel.egyMeteors.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }

}