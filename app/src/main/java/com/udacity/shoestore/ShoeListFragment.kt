package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import com.udacity.shoestore.databinding.FragmentShoelistBinding

class ShoeListFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentShoelistBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoelist, container, false)
        return binding.root
    }
}