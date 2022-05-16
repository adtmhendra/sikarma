package com.example.sikarma.presentation.view.admin.authentication

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sikarma.databinding.FragmentRegisterBinding
import com.example.sikarma.presentation.viewmodel.LoginViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by activityViewModels()

    private lateinit var edtUsername: TextInputEditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var btnRegister: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        edtUsername = binding.edtUsername
        edtPassword = binding.edtPassword
        btnRegister = binding.btnRegister

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtUsername.requestFocus()

        edtUsername.addTextChangedListener(registerTextWatcher)
        edtPassword.addTextChangedListener(registerTextWatcher)

        btnRegister.setOnClickListener { addNewAdmin() }
    }

    private fun addNewAdmin() {
        if (!isDataExists(edtUsername.text.toString().trim())) {
            viewModel.addNewAdmin(
                edtUsername.text.toString().trim().lowercase(),
                edtPassword.text.toString().trim().lowercase()
            )
            Toast.makeText(activity,
                "Register akun berhasil. Silahkan login menggunakan akun yang telah dibuat",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        } else {
            hideKeyboard()
            Snackbar.make(btnRegister, "Akun telah terdaftar", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun isDataExists(username: String) =
        viewModel.getAdminUsername(username)

    private val registerTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            btnRegister.isEnabled =
                edtUsername.text!!.isNotEmpty() && edtPassword.text!!.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    private fun hideKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val inputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}