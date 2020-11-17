package com.udacity.shoestore

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.databinding.FragmentShoedetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoeList.ShoeListFragment
import kotlinx.android.synthetic.main.fragment_shoedetail.*
import timber.log.Timber

class DetailActivity: AppCompatActivity() {

    private lateinit var binding: FragmentShoedetailBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_shoedetail)
        setSupportActionBar(binding.toolbar2)

        intent?.let{
            val parcel = intent.extras?.getParcelable(ShoeListFragment.shoeCheckedKey) as Shoe?
            if (parcel != null) {
                shoeName_TextView.text = parcel.name
                shoeCompany_TextView.text = parcel.company
                shoeSize_TextView.text = parcel.size.toString()
                shoeDescription_TextView.text = parcel.description
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menuInflater.inflate(R.menu.logout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }
}