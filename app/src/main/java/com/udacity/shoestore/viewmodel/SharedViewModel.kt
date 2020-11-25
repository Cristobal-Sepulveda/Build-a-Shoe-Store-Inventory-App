package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User

class SharedViewModel: ViewModel()
{
    private val _listOfShoes = MutableLiveData<MutableList<Shoe>>()
    val listOfShoes: LiveData<MutableList<Shoe>>
        get() = _listOfShoes

    private val _listOfUsers = MutableLiveData<MutableList<User>>()
    private val listOfUsers: LiveData<MutableList<User>>
        get() = _listOfUsers

    // >>>>>>>>>>>>>>>>>>>>>>>>>>Methods<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< //
    fun addShoe(newShoe: Shoe){
        if (listOfShoes.value == null) {
            _listOfShoes.value = mutableListOf(newShoe)
        } else {
            listOfShoes.value!!.add(newShoe)
        }
    }

    fun addUser(newUser: User){
        if (listOfUsers.value == null) {
            _listOfUsers.value = mutableListOf(newUser)
        }else{
            listOfUsers.value!!.add(newUser)
        }
    }

    fun consultUserExist(newUser: User): Boolean {
        if(_listOfUsers.value == null){
            return false
        }
        for (i in _listOfUsers.value!!.indices) {
            if (newUser == _listOfUsers.value!![i]) {
                return true
            }
        }
        return false
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<< //
}


