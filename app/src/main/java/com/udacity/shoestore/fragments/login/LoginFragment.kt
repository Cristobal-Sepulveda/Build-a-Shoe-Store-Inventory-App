package com.udacity.shoestore.fragments.login

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
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.viewmodel.SharedViewModel
import com.udacity.shoestore.models.User
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber

class LoginFragment: Fragment() {

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        binding.sharedViewModel = viewModel
        binding.loginFragment= this
        binding.lifecycleOwner = this

        Timber.i("${viewModel.listOfShoes.value}")

        return binding.root
    }

    // >>>>>>>>>>>>>>>>> Button's Method's <<<<<<<<<<<<<<<<<<<< //
    /** Here, first, i make a User object with the text from the editText's. then, i check if the
     *  username text is empty, has a valid email text (format), or if the user already exist,
     *  if any of these conditions occur the method returns a toast.
     * ON THE OTHER HAND, IF ALL IS GOOD, i add a newUSer with the viewModel method and navigate
     * to WelcomeFragment*/
    fun createAccountButtonMethod() {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val newUser = User(loginFragmentUserName_editText.text.toString(),
                loginFragmentUserPassword_editText.text.toString())

        if (loginFragmentUserName_editText.text.isEmpty() ||
                loginFragmentUserPassword_editText.text.isEmpty()) {

            Toast.makeText(this@LoginFragment.context,
                    "You must fill the boxes with your Email & Password",
                    Toast.LENGTH_SHORT).show()
            return
        }

        if(!loginFragmentUserName_editText.text.toString().trim().matches(emailPattern.toRegex())){
            Toast.makeText(this@LoginFragment.context,
                    "Please fill the userName with a valid Email",
                    Toast.LENGTH_SHORT).show()
            return
        }

        if (viewModel.consultUserExist(newUser.userName)){
            Toast.makeText(this@LoginFragment.context,
                    "User already exist",
                    Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.addUser(newUser)
        loginFragmentUserName_editText.text.clear()
        loginFragmentUserPassword_editText.text.clear()
        createAccount_button.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }

    /** Here, first, i make a User object with the text from the editText's. then, i check if the
    *  username text is empty, has a valid email text (format). then i check if the user exist
     *  or if the user don't exit exist or
     *  the password,
    *  if any of these conditions occur the method returns a toast.
    * ON THE OTHER HAND, IF ALL IS GOOD, i add a newUSer with the viewModel method and navigate
    * to WelcomeFragment */
    fun loginButtonMethod() {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val newUser = User(loginFragmentUserName_editText.text.toString(),
                loginFragmentUserPassword_editText.text.toString())

        if (newUser.userName.isEmpty() ||
                newUser.userPassword.isEmpty()) {

            Toast.makeText(this@LoginFragment.context,
                    "You must fill your Email & Password",
                    Toast.LENGTH_SHORT).show()
            return
        }

        if (!newUser.userName.trim().matches(emailPattern.toRegex())) {
            Toast.makeText(this@LoginFragment.context,
                    "Please fill the userName with a valid Email", Toast.LENGTH_SHORT).show()
            return
        }

        when (viewModel.canILogin(newUser)){
            1 ->Toast.makeText(this@LoginFragment.context,
                    "This Mail Doesn't have an Account",
                    Toast.LENGTH_SHORT).show()
            2 ->Toast.makeText(this@LoginFragment.context,
                    "Wrong Password",
                    Toast.LENGTH_SHORT).show()
            3 ->login_button.findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToShoeListFragment())
        }
        return
    }
    // >>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<< //
}