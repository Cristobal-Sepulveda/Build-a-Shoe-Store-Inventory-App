 package com.udacity.shoestore.shoeList

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.DetailActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoelistBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoelist.*
import kotlinx.android.synthetic.main.fragment_shoelist.view.*

class ShoeListFragment: Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    companion object {
        const val shoeCheckedKey = "shoeChecked"
    }
    lateinit var radiobutton: RadioButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentShoelistBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_shoelist, container, false)
        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        binding.shoeListViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root

    }
    fun FAB (){
        if (viewModel.shoeChecked ==null) {
            Toast.makeText(this@ShoeListFragment.context, "Select a Shoe first to see his Details", Toast.LENGTH_LONG).show()
        }else {
            viewModel.listOfShoes.observe(this,  Observer{
                var i = 0
                if(viewModel.shoeChecked != it[i].company){
                    i += 1
                }else{
                    var parcel = Shoe(it[i].name, it[i].size, it[i].company, it[i].description)
                    val intent = Intent(this@ShoeListFragment.context, DetailActivity::class.java)
                    intent.putExtra(shoeCheckedKey, viewModel.shoeChecked)
                    startActivity(intent)
                }
            })
        }
    }
    fun checkButton(){
        var radioId = radioGroup.checkedRadioButtonId
        Toast.makeText(this@ShoeListFragment.context,"$radioId", Toast.LENGTH_LONG).show()
    }
}