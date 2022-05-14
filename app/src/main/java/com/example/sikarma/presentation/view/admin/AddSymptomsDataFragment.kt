package com.example.sikarma.presentation.view.admin

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sikarma.R
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.databinding.FragmentAddSymptomsDataBinding
import com.example.sikarma.presentation.viewmodel.SymptomsViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSymptomsDataFragment : Fragment() {

    private var _binding: FragmentAddSymptomsDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SymptomsViewModel by activityViewModels()

    private lateinit var edtSymptomName: TextInputEditText
    private lateinit var btnSave: MaterialButton

    private val navigationArgs: AddSymptomsDataFragmentArgs by navArgs()

    private lateinit var symptoms: Symptoms

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

        edtSymptomName.requestFocus()

        edtSymptomName.addTextChangedListener(addSymptomsTextWatcher)

        val id = navigationArgs.idSymptoms
        if (id > 0) {
            viewModel.retrieveSymptoms(id).observe(viewLifecycleOwner) { selectedItem ->
                symptoms = selectedItem
                bind(symptoms)
            }
        } else {
            btnSave.setOnClickListener { addNewSymptoms() }
        }
    }

    // Go back to symptoms main page if data is successfully added
    private fun addNewSymptoms() {
        val symptomName = edtSymptomName.text.toString().trim().lowercase()
        if (!isDataExist(symptomName)) {
            viewModel.addNewSymptoms(symptomName)
            findNavController().navigate(R.id.action_addSymptomsDataFragment_to_symptomsDataFragment)
            Toast.makeText(requireContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT)
                .show()
        } else {
            hideKeyboard()
            Snackbar.make(btnSave, "Data telah tersedia", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun updateSymptoms() {
        val symptomName = edtSymptomName.text.toString().trim().lowercase()
        if (!isDataExist(symptomName)) {
            viewModel.updateSymptoms(this.navigationArgs.idSymptoms, symptomName)
            Toast.makeText(activity, "Berhasil memperbarui data", Toast.LENGTH_SHORT).show()
            findNavController().navigate(AddSymptomsDataFragmentDirections.actionAddSymptomsDataFragmentToSymptomsDataFragment())
        } else {
            hideKeyboard()
            Snackbar.make(btnSave, "Data telah tersedia", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun bind(symptoms: Symptoms) {
        edtSymptomName.setText(symptoms.symptoms, TextView.BufferType.SPANNABLE)
        btnSave.setOnClickListener { updateSymptoms() }
    }

    // Check is data exists?
    private fun isDataExist(symptoms: String) =
        viewModel.checkData(symptoms)

    // Disable save button when symptoms edit text is empty
    private val addSymptomsTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            btnSave.isEnabled = edtSymptomName.text!!.isNotEmpty()
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
        hideKeyboard()
        _binding = null
    }
}