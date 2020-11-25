package com.udacity.shoestore.fragments.shoeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoedetailBinding
import com.udacity.shoestore.viewmodel.SharedViewModel
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoedetail.*
import timber.log.Timber

class ShoeDetail: Fragment() {

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentShoedetailBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_shoedetail, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        binding.sharedViewModel = viewModel
        binding.shoeDetailFragment = this
        binding.lifecycleOwner = this

        //this is just for see the viewModel is working fine
        Timber.i("${viewModel.listOfShoes.value}")

        return binding.root

    }

    // >>>>>>>>>>>>>>>>> Button's Method's <<<<<<<<<<<<<<<<<<<< //
    /** here in saveButton i add the text's to a Shoe object and then check if it have empty values, if this is true
     * i toast a message and return,  if doesn't,  i put the Shoe Object in the viewModel
     * (I DONT CHECK IF THE editText.SIZE.text IS DOUBLE BECAUSE I SET
     * the input Type of that editText as Number decimal, so, that condition do the job for me*/
    fun saveButton() {
        val newShoe = Shoe(shoeDetailCreateName_editText.text.toString(),
                shoeDetailCreateSize_editText.text.toString().toDouble(),
                shoeDetailCreateCompany_editText.text.toString(),
                shoeDetailCreateDescription_editText.text.toString())

        if (newShoe.name.isEmpty()||
                shoeDetailCreateSize_editText.text.toString().isEmpty() ||
                newShoe.company.isEmpty() ||
                newShoe.description.isEmpty()) {

            Toast.makeText(this@ShoeDetail.context,
                    "You must fill in all the boxes to make a new shoe",
                    Toast.LENGTH_SHORT).show()
            return
        } else {

            viewModel.addShoe(newShoe)
            // just for see if the addShoe method works
            Timber.i("${viewModel.listOfShoes.value}")
            shoeDetailSaveButton_button.findNavController().navigate(
                    ShoeDetailDirections.actionShoeDetailFragmentToShoeListFragment())
        }
    }

    /** this is for navigate back, nothing else*/
    fun cancelButton() {
        shoeDetailCancelButton_button.findNavController().navigate(
                ShoeDetailDirections.actionShoeDetailFragmentToShoeListFragment())
    }
    // >>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<< //
}