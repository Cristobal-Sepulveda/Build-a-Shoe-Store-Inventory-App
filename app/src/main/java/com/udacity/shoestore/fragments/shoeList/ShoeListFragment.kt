package com.udacity.shoestore.fragments.shoeList

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.udacity.shoestore.fragments.shoeDetail.ShoeDetail
import com.udacity.shoestore.fragments.welcome.WelcomeFragment
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoelist.*
import timber.log.Timber

class ShoeListFragment: Fragment() {
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

            if (viewModel.listChange.value == null) {
                val adapter = this@ShoeListFragment.context?.let { it1 -> ShoeAdapter(it1, listItems) }
                listOfView.adapter = adapter
            } else {
                println(listItems)
                val adapter = this@ShoeListFragment.context?.let { it1 -> ShoeAdapter(it1, listItems) }
                listOfView.adapter = adapter
                viewModel.listUpdated()
            }
        })

        val args = ShoeListFragmentArgs.fromBundle(arguments!!)
        Log.i("test", "${args.listOfShoesBundle.last()}\n bundle recibido de detail")

        return binding.root
    }

    fun navigationFAB() {
        val args = ShoeListFragmentArgs.fromBundle(arguments!!)
        val listItems = arrayOfNulls<Shoe>(args.listOfShoesBundle.size)

        for (i in args.listOfShoesBundle.indices) {
            val shoe = args.listOfShoesBundle[i]
            listItems[i] = shoe
        }

        Log.i("test", "${listItems.last()} \n bundle enviado a detail")

        floatingActionButton.findNavController().navigate(
                    ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment(listItems))
    }

}



