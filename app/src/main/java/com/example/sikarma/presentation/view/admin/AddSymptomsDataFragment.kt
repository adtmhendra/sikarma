package com.example.sikarma.presentation.view.admin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sikarma.R
import com.example.sikarma.databinding.FragmentAddSymptomsDataBinding
import com.example.sikarma.presentation.viewmodel.SymptomsViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSymptomsDataFragment : Fragment() {

    private var _binding: FragmentAddSymptomsDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SymptomsViewModel by viewModels()

    private lateinit var edtSymptomName: TextInputEditText
    private lateinit var btnSave: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddSymptomsDataBinding.inflate(inflater, container, false)

        edtSymptomName = binding.edtSymptomName
        btnSave = binding.btnSave

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtSymptomName.addTextChangedListener(addSymptomsTextWatcher)

        btnSave.setOnClickListener { goToSymptomsDataFragment() }
    }

    // Go back to symptoms main page when data is successfully added
    private fun goToSymptomsDataFragment() {
        if (isNotBlank()) {
            viewModel.addNewSymptoms(edtSymptomName.text.toString())
            findNavController().navigate(R.id.action_addSymptomsDataFragment_to_symptomsDataFragment)
            Toast.makeText(requireContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(requireContext(), "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
    }

    // Check is symptoms edit text empty?
    private fun isNotBlank() =
        viewModel.isNotBlank(edtSymptomName.text.toString())

    // Disable save button when symptoms edit text is empty
    private val addSymptomsTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            btnSave.isEnabled = edtSymptomName.text!!.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}