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
import com.udacity.shoestore.fragments.shoeList.ShoeListViewModel
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoelist.*

class InstructionsFragment: Fragment() {
    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentInstructionsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instructions, container, false)
        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)

        binding.lifecycleOwner = this
        binding.shoeListViewModel = viewModel

        binding.instructionsButton.setOnClickListener { v: View ->
            viewModel.listOfShoes.observe(viewLifecycleOwner, Observer {
                val listItems = arrayOfNulls<Shoe>(it.size)

                for (i in 0 until it.size) {
                    val shoe = it[i]
                    listItems[i] = shoe
                }

                v.findNavController().navigate(InstructionsFragmentDirections.
                actionInstructionsFragmentToShoeListFragment(listItems))
            })
        }

        return binding.root
    }

}