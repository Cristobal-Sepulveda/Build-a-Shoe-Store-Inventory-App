package com.udacity.shoestore.fragments.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.fragments.shoeDetail.ShoeDetailDirections
import com.udacity.shoestore.fragments.shoeList.ShoeListFragmentArgs
import com.udacity.shoestore.fragments.shoeList.ShoeListViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_shoedetail.*
import timber.log.Timber

class LoginFragment: Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        binding.loginFragment= this
        binding.shoeListViewModel = viewModel

        val args = arguments?.let { LoginFragmentArgs.fromBundle(it) }
        Log.i("test", "${args.toString()}")
        val args2 = arguments?.let { ShoeListFragmentArgs.fromBundle(it) }
        if (args2 != null) {
            for (element in args2.listOfUsersBundle) {
                Log.i("test", "${element} \n bundle users recibido en login desde list")
            }
        }
        return binding.root
    }
    fun createAccountButtonMethod() {
        if (loginFragmentUserName_editText.text.isEmpty() || loginFragmentUserPassword_editText.text.isEmpty()) {
            Toast.makeText(this@LoginFragment.context,
                    "You must fill your Email & Password",
                    Toast.LENGTH_SHORT).show()
            return
        }

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if(loginFragmentUserName_editText.toString().trim().matches(emailPattern.toRegex())){
            Toast.makeText(this@LoginFragment.context,
                    "Please fill the userName with a valid Email", Toast.LENGTH_SHORT).show()
        return
        }

        val args = arguments?.let { LoginFragmentArgs.fromBundle(it) }

        if (args == null){
            val user = User(loginFragmentUserName_editText.text.toString(), loginFragmentUserPassword_editText.text.toString())
            val arrayUsers = arrayOfNulls<User>(1)
            arrayUsers[0] = user
            Log.i("test","${arrayUsers.last()} lista usuario creada con este usuario")
            createAccount_button.findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(arrayUsers))
        }else {
            val arrayUsers = arrayOfNulls<User>(args.listOfUsersBundle.size + 1)
            for (i in 0 until (arrayUsers.size -1)){
                val user = args.listOfUsersBundle[i]
                arrayUsers[i] = user
            }
            val newUser = User(loginFragmentUserName_editText.text.toString(),
                    loginFragmentUserPassword_editText.text.toString())

            if (userExist(newUser)){
                Toast.makeText(this@LoginFragment.context, "User already exist", Toast.LENGTH_LONG).show()
            }else{
                arrayUsers[args.listOfUsersBundle.size] = newUser
                createAccount_button.findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(arrayUsers))
            }
        }
    }

    fun loginButtonMethod() {

        if (loginFragmentUserName_editText.text.isEmpty() || loginFragmentUserPassword_editText.text.isEmpty()) {
            Toast.makeText(this@LoginFragment.context,
                    "You must fill your Email & Password",
                    Toast.LENGTH_SHORT).show()
            return
        }
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if(loginFragmentUserName_editText.text.toString().trim().matches(emailPattern.toRegex())){
            Toast.makeText(this@LoginFragment.context,
                    "Please fill the userName with a valid Email", Toast.LENGTH_SHORT).show()
            return
        }

        val args = arguments?.let { LoginFragmentArgs.fromBundle(it) }

        if (args == null){
            Toast.makeText(this@LoginFragment.context, "Invalid UserName or Password", Toast.LENGTH_SHORT).show()
            return
        }else{

            val userToCheck = User(loginFragmentUserName_editText.text.toString(),
                    loginFragmentUserPassword_editText.text.toString())

            if (userExist(userToCheck)){
                Toast.makeText(this@LoginFragment.context, "User already exist", Toast.LENGTH_LONG).show()
            }else{
                createAccount_button.findNavController().navigate(
                        LoginFragmentDirections.
                        actionLoginFragmentToShoeListFragment
                        (args.listOfShoesBundle, args.listOfUsersBundle))
            }

        }
    }

    fun userExist(user: User):Boolean{
        val args = LoginFragmentArgs.fromBundle(arguments!!)
        var i = 0
        while (i< args.listOfUsersBundle.size - 1) {
            if (user != args.listOfUsersBundle[i]) {
                i++
            } else {
                return true
            }
        }
        return false
    }
}