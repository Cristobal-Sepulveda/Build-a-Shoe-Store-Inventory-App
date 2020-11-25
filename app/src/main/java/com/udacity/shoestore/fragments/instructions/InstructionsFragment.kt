package com.udacity.shoestore.fragments.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.viewmodel.SharedViewModel
import com.udacity.shoestore.models.Shoe

class InstructionsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentInstructionsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instructions, container, false)

        binding.instructionsButton.setOnClickListener { v: View ->
            v.findNavController().navigate(
                    InstructionsFragmentDirections.
                    actionInstructionsFragmentToShoeListFragment())
        }

        return binding.root
    }
}