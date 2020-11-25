package com.udacity.shoestore.fragments.shoeList

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.udacity.shoestore.R
import com.udacity.shoestore.adapter.ShoeAdapter
import com.udacity.shoestore.databinding.FragmentShoelistBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_shoelist.*
import timber.log.Timber

class ShoeListFragment: Fragment() {
    private lateinit var viewModel: SharedViewModel
    private var  parentLinearLayout: LinearLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentShoelistBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_shoelist, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        binding.sharedViewModel = viewModel
        binding.shoeListFragment = this
        binding.lifecycleOwner = this
        parentLinearLayout = binding.list
        setHasOptionsMenu(true)

        //this is just for see the viewModel is working fine
        Timber.i("${viewModel.listOfShoes.value}")

        viewModel.listOfShoes.observe(viewLifecycleOwner, Observer {
            for (i in it.indices) {
                val shoe = it[i]
                onAddField(shoe)
            }
        })

        return binding.root
    }

    // here im creating the logout menu in the toolbar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout, menu)
    }

    // >>>>>>>>>>>>>>>>> METHODS <<<<<<<<<<<<<<<<<<<< //
    /** View Maker */
    private fun onAddField(newShoe: Shoe) {
        val inflater= context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.card_item, null)
        val rowViewName = rowView.findViewById<TextView>(R.id.nameText)
        val rowViewCompany = rowView.findViewById<TextView>(R.id.companyText)
        val rowViewSize = rowView.findViewById<TextView>(R.id.sizeText)
        val rowViewDescription = rowView.findViewById<TextView>(R.id.descriptionText)
        rowViewName.text = newShoe.name
        rowViewCompany.text = newShoe.company
        rowViewSize.text = newShoe.size.toString()
        rowViewDescription.text = newShoe.description
        parentLinearLayout!!.addView(rowView, parentLinearLayout!!.childCount - 1)
    }
    /** FAB */
    fun navigationFAB() {
        floatingActionButton.findNavController().navigate(
                    ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
    }
    // >>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<< //
}



