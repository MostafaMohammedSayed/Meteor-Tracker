package com.example.android.meteortracker.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.android.meteortracker.databinding.FragmentDetailBinding
import android.content.Intent
import android.net.Uri


class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this


        val meteor = DetailFragmentArgs.fromBundle(requireArguments()).selectedMeteor

        binding.meteor = meteor

        binding.navigateButton.setOnClickListener {
            val mapUri: Uri = Uri.parse("geo:0,0?q=${meteor.lat},${meteor.lng}(label)")
            val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }


        return binding.root
    }

}
