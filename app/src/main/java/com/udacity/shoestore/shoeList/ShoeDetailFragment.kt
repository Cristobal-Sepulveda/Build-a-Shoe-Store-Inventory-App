package com.udacity.shoestore.shoeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoedetailBinding

class ShoeDetail: Fragment() {
    private lateinit var  viewModel: ShoeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentShoedetailBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_shoedetail, container, false)
        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        binding.shoeListViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }


}