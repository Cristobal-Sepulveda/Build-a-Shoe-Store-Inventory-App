
package com.udacity.shoestore.shoeList

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.DetailActivity
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoelist.*
import java.security.AccessController.getContext

class ShoeListViewModel: ViewModel() {
    var shoeChecked: String? = null

    private val _listOfShoes = MutableLiveData<MutableList<Shoe>>()
    val listOfShoes: LiveData<MutableList<Shoe>>
        get() = _listOfShoes
    init {
        _listOfShoes.value = mutableListOf(
                Shoe("zapato de cuero", 40.0, "Azaleia", "zapato negro de excelente calidad"),
                Shoe("zapatilla de tela", 38.5, "Bata", "zapatila de verano"),
                Shoe("zapatilla caña alta", 44.5, "Converse", "zapatilla negra con blanco"),
                Shoe("zapatilla de skate", 42.0, "DC", "zapatilla ancha suela plana"),
                Shoe("zapatilla spike", 45.0, "Element", "zapatilla de vestir"),
                Shoe("zapatilla fila", 36.0, "Fila", "zapatilla blanca con plataforma"))
        }

}


