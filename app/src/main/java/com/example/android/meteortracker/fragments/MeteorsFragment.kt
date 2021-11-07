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


class MeteorsFragment : Fragment() {

    private lateinit var binding: FragmentMeteorsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMeteorsBinding.inflate(inflater)

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
                MeteorsFragmentDirections.actionMeteorsFragmentToDetailFragment(it))

                viewModel.onNavigationCompleted() }
        })

        binding.meteorRecycler.adapter = adapter


        viewModel.meteors.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.egyMeteors->findNavController().navigate(
                MeteorsFragmentDirections.actionMeteorsFragmentToEgyMeteorsFragment()
            )
        }
        return super.onOptionsItemSelected(item)
    }
}