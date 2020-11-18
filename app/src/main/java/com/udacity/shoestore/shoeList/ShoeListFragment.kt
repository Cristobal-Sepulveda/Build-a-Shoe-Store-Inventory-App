
 package com.udacity.shoestore.shoeList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoelistBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoelist.*

 class ShoeListFragment: Fragment()
{
    private lateinit var viewModel: ShoeListViewModel
            override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
                val binding: FragmentShoelistBinding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_shoelist, container, false)
                viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)

                binding.shoeListViewModel = viewModel
                binding.lifecycleOwner = this

                floatingActionButton.setOnClickListener { v:View ->
                    v.findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
                }

                return binding.root
            }
}
