
 package com.udacity.shoestore.shoeList

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.internal.ContextUtils
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.DetailActivity
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoelistBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoelist.*
import kotlinx.android.synthetic.main.fragment_shoelist.view.*
//
class ShoeListFragment: Fragment()
{
    private lateinit var viewModel: ShoeListViewModel
    private lateinit var shoeChecked: String
    companion object { const val shoeCheckedKey = "shoeChecked" }

            override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
                val binding: FragmentShoelistBinding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_shoelist, container, false)
                viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
                binding.shoeListViewModel = viewModel
                binding.lifecycleOwner = this

                return binding.root
            }

    /** Methods */
        /*fun FAB () {
            if (radioGroup.checkedRadioButtonId == -1) {
            Toast.makeText(this@ShoeListFragment.context,
                    "Select a Shoe first to see his Details",
                    Toast.LENGTH_LONG).show()
            }
            else
            {
                if(button1_RadioButton.isChecked){ shoeChecked = companyText1.toString() }
                if(button2_RadioButton.isChecked){ shoeChecked = companyText2.toString() }
                if(button3_RadioButton.isChecked){ shoeChecked = companyText3.toString() }
                if(button4_RadioButton.isChecked){ shoeChecked = companyText4.toString() }
                if(button5_RadioButton.isChecked){ shoeChecked = companyText5.toString() }
                if(button6_RadioButton.isChecked){ shoeChecked = companyText6.toString()}

                viewModel.listOfShoes.observe(viewLifecycleOwner,  Observer{
                    var i = 0
                    if(shoeChecked != it[i].company){
                        i += 1
                    }
                    else
                    {
                        val parcel = Shoe(it[i].name, it[i].size, it[i].company, it[i].description)
                        val intent = Intent(this@ShoeListFragment.context, DetailActivity::class.java)
                        intent.putExtra(shoeCheckedKey, parcel)
                        startActivity(intent)
                    }
                })
            }
        }*/
        /*floatingActionButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@ShoeListFragment.context, "asd", Toast.LENGTH_LONG).show()}
         })*/
        fun FAB(){
            Toast.makeText(this@ShoeListFragment.context ,"asd", Toast.LENGTH_LONG).show()
        }
}
