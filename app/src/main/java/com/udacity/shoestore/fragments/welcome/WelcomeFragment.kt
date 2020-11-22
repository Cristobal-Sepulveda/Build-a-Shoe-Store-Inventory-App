package com.udacity.shoestore.fragments.welcome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false)

        var args = WelcomeFragmentArgs.fromBundle(arguments!!)
        for (element in args.listOfUsersBundle) {
            Log.i("test", "${element} \n bundle user en welcome")
        }
        binding.welcomeButton.setOnClickListener { v:View ->
            v.findNavController().navigate(
                    WelcomeFragmentDirections.
                    actionWelcomeFragmentToInstructionsFragment
                    (args.listOfUsersBundle))
        }

        return binding.root
    }
}