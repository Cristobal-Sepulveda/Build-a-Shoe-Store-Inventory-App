
package com.udacity.shoestore.fragments.shoeList

import android.widget.ListView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe


class ShoeListViewModel: ViewModel()
{
    private val lista = mutableListOf(
            Shoe("zapato de cuero", 40.0, "Azaleia", "zapato negro de excelente calidad"),
            Shoe("zapatilla de tela", 38.5, "Bata", "zapatila de verano"),
            Shoe("zapatilla caña alta", 44.5, "Converse", "zapatilla negra con blanco"),
            Shoe("zapatilla de skate", 42.0, "DC", "zapatilla ancha suela plana"),
            Shoe("zapatilla spike", 45.0, "Element", "zapatilla de vestir"),
            Shoe("zapatilla fila", 36.0, "Fila", "zapatilla blanca con plataforma"))

    private val _listOfShoes = MutableLiveData(lista)
    val listOfShoes: LiveData<MutableList<Shoe>>
        get() = _listOfShoes

    private val _listChange = MutableLiveData<Boolean>()
    val listChange: LiveData<Boolean>
        get()=_listChange

    fun addShoe(shoe: Shoe){
        lista.add(shoe)
        _listOfShoes.value = lista
        _listChange.value = true
    }
    fun listUpdated(){
        _listChange.value = null
    }
}


