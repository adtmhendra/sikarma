package com.example.sikarma.view.admin

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sikarma.R
import com.example.sikarma.databinding.FragmentLoginBinding
import com.example.sikarma.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var edtUsername: TextInputEditText
    private lateinit var edtPassword: TextInputEditText

    private lateinit var getAdminUsername: String
    private lateinit var getAdminPassword: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        edtUsername = binding.edtUsername
        edtPassword = binding.edtPassword

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtUsername.addTextChangedListener(loginTextWatcher)
        edtPassword.addTextChangedListener(loginTextWatcher)

        edtUsername.requestFocus()

        doAdminLogin()
    }

    // Admin login
    private fun doAdminLogin() {
        binding.btnSignIn.setOnClickListener {
            getAdminUsername = edtUsername.text.toString().trim()
            getAdminPassword = edtPassword.text.toString().trim()

            checkAccount(adminUsername = getAdminUsername, adminPassword = getAdminPassword)
            hideKeyboard()
        }
    }

    // Check if text view is not empty
    private fun checkAccount(adminUsername: String, adminPassword: String) {
        if (viewModel.isNotBlank(adminUsername, adminPassword))
            checkIsDataValid(adminUsername, adminPassword)
    }

    // Check is account exists in database?
    private fun checkIsDataValid(adminUsername: String, adminPassword: String) {
        lifecycleScope.launch {
            viewModel.checkAdminDataAccount(adminUsername, adminPassword).collect { admin ->
                try {
                    if (adminUsername == admin.username && adminPassword == admin.password) goToDashboardAdmin()
                    clearText()
                    Log.d(TAG, "Selamat datang, ${admin.username}")
                } catch (e: Exception) {
                    Snackbar.make(binding.btnSignIn,
                        getString(R.string.snackbar_invalid_username_password),
                        Snackbar.LENGTH_SHORT).show()
                    clearText()
                }
            }
        }
    }

    private fun goToDashboardAdmin() {
        findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.btnSignIn.isEnabled =
                edtUsername.text!!.isNotEmpty() && edtPassword.text!!.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    private fun hideKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val inputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun clearText() {
        edtUsername.text?.clear()
        edtPassword.text?.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }
}