package com.udacity.shoestore.fragments.shoeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.adapter.ShoeAdapter
import com.udacity.shoestore.databinding.FragmentShoelistBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoelist.*

 class ShoeListFragment: Fragment()
{
    lateinit var listOfView: ListView
    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {

        val binding: FragmentShoelistBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_shoelist, container, false)

        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        binding.shoeListViewModel = viewModel
        binding.lifecycleOwner = this
        binding.shoeListFragment = this

        viewModel.listOfShoes.observe(viewLifecycleOwner, Observer {
            listOfView = shoeList_listView
            val listItems = arrayOfNulls<Shoe>(it.size)
            for (i in 0 until it.size) {
                val shoe = it[i]
                listItems[i] = shoe
            }
            val adapter = this@ShoeListFragment.context?.let { it1 -> ShoeAdapter(it1, listItems) }
            if (viewModel.listChange.value == null){
                listOfView.adapter = adapter}
            else{
                adapter?.notifyDataSetChanged()
                listOfView.adapter = adapter
                viewModel.listUpdated()
            }

        })
        return binding.root
    }

    fun navigationFAB(){
            floatingActionButton.findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }
    }


