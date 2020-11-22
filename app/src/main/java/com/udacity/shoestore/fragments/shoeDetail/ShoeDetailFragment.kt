package com.udacity.shoestore.fragments.shoeDetail

import android.os.Bundle
import android.util.Log
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
import com.udacity.shoestore.fragments.shoeList.ShoeListFragmentArgs
import com.udacity.shoestore.fragments.shoeList.ShoeListViewModel
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoedetail.*
import kotlinx.android.synthetic.main.fragment_shoelist.*

class ShoeDetail: Fragment() {
    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentShoedetailBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_shoedetail, container, false)
        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)

        binding.shoeListViewModel = viewModel
        binding.lifecycleOwner = this
        binding.shoeDetailFragment = this

        val args = ShoeDetailArgs.fromBundle(arguments!!)
        for (element in args.listOfShoesBundle) {
            Log.i("test", "${element} \n bundle recibido en details")
        }
        for (element in args.listOfUsersBundle) {
            Log.i("test", "${element} \n bundle user recibido en detail")
        }
        return binding.root

    }

    fun saveButton() {

        val isDouble = (shoeDetailCreateSize_editText.text.toString()).toDoubleOrNull()

        if (shoeDetailCreateName_editText.text.isEmpty() ||
                shoeDetailCreateCompany_editText.text.isEmpty() ||
                shoeDetailCreateSize_editText.text.isEmpty() ||
                shoeDetailCreateDescription_editText.text.isEmpty()) {

            Toast.makeText(this@ShoeDetail.context,
                    "You must fill in all the boxes to make a new shoe",
                    Toast.LENGTH_SHORT).show()
        }

        if (isDouble == null) {
            Toast.makeText(this@ShoeDetail.context,
                    "You must fill the Shoe size as a Boolean type Number",
                    Toast.LENGTH_SHORT).show()
        } else {

            val args = ShoeDetailArgs.fromBundle(arguments!!)
            val listItems = arrayOfNulls<Shoe>(args.listOfShoesBundle.size + 1)

            for (i in 0 until (listItems.size -1)){
                    val shoe = args.listOfShoesBundle[i]
                    listItems[i] = shoe
                }
            listItems[args.listOfShoesBundle.size] = Shoe(shoeDetailCreateName_editText.text.toString(),
                    shoeDetailCreateSize_editText.text.toString().toDouble(),
                    shoeDetailCreateCompany_editText.text.toString(),
                    shoeDetailCreateDescription_editText.text.toString())
                Log.i("test", "${listItems.last()} \n bundle pasado a list desde save")

            shoeDetailSaveButton_button.findNavController().navigate(
                    ShoeDetailDirections.actionShoeDetailFragmentToShoeListFragment(listItems,args.listOfUsersBundle))
        }
    }

    fun cancelButton(){
        val args = ShoeDetailArgs.fromBundle(arguments!!)
        val listItems = arrayOfNulls<Shoe>(args.listOfShoesBundle.size)

        for (i in listItems.indices){
            val shoe = args.listOfShoesBundle[i]
            listItems[i] = shoe
        }
        Log.i("test", "${listItems.last()} \n bundle pasado a list desde cancel")
        shoeDetailCancelButton_button.findNavController().navigate(ShoeDetailDirections.
        actionShoeDetailFragmentToShoeListFragment(listItems, args.listOfUsersBundle ))
    }

}