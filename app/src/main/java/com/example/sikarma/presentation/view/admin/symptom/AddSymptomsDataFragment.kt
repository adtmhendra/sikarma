package com.example.sikarma.presentation.view.admin.symptom

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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSymptomsDataFragment : Fragment() {

    private var _binding: FragmentAddSymptomsDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SymptomsViewModel by activityViewModels()

    private lateinit var edtSymptomName: TextInputEditText
    private lateinit var edtSymptomCode: TextInputEditText
    private lateinit var symptomCode: TextInputLayout
    private lateinit var btnSave: MaterialButton

    private lateinit var prefix: String
    private lateinit var getSymptomCode: String

    private val navigationArgs: AddSymptomsDataFragmentArgs by navArgs()

    private lateinit var symptoms: Symptoms

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddSymptomsDataBinding.inflate(inflater, container, false)

        symptomCode = binding.symptomCode
        edtSymptomName = binding.edtSymptomName
        edtSymptomCode = binding.edtSymptomCode
        btnSave = binding.btnSave

        prefix = symptomCode.prefixText.toString()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtSymptomCode.requestFocus()

        edtSymptomName.addTextChangedListener(addSymptomsTextWatcher)
        edtSymptomCode.addTextChangedListener(addSymptomsTextWatcher)

        val id = navigationArgs.idSymptoms
        if (id > 0) {
            edtSymptomCode.isEnabled = false
            symptomCode.prefixText = ""
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
        getSymptomCode = prefix + edtSymptomCode.text.toString()
        if (!isDataExist(getSymptomCode, edtSymptomName.text.toString().trim().lowercase())) {
            viewModel.addNewSymptoms(
                getSymptomCode,
                edtSymptomName.text.toString().trim().lowercase(),
            )
            Toast.makeText(activity, "Data berhasil disimpan", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_addSymptomsDataFragment_to_symptomsDataFragment)
        } else {
            hideKeyboard()
            Snackbar.make(btnSave, "Data telah tersedia", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun updateSymptoms() {
        getSymptomCode = edtSymptomCode.text.toString()
        if (isDataExist(getSymptomCode, edtSymptomName.text.toString().trim().lowercase())) {
            viewModel.updateSymptoms(
                this.navigationArgs.idSymptoms,
                getSymptomCode,
                edtSymptomName.text.toString().trim().lowercase())
            Toast.makeText(activity, "Berhasil memperbarui data", Toast.LENGTH_SHORT).show()
            findNavController().navigate(AddSymptomsDataFragmentDirections.actionAddSymptomsDataFragmentToSymptomsDataFragment())
        } else {
            hideKeyboard()
            Snackbar.make(btnSave, "Data telah tersedia", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun bind(symptoms: Symptoms) {
        edtSymptomCode.setText(symptoms.symptoms_code, TextView.BufferType.SPANNABLE)
        edtSymptomName.setText(symptoms.symptoms_name, TextView.BufferType.SPANNABLE)
        btnSave.setOnClickListener { showConfirmationDialog() }
    }

    // Check is data exists?
    private fun isDataExist(symptomsCode: String, symptomsName: String) =
        viewModel.checkData(symptomsCode, symptomsName)

    // Disable save button when symptoms edit text is empty
    private val addSymptomsTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            btnSave.isEnabled =
                edtSymptomCode.text!!.isNotEmpty() && edtSymptomName.text!!.isNotEmpty()
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

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Konfirmasi")
            .setMessage("Yakin ingin mengubah data?")
            .setCancelable(false)
            .setNegativeButton("Batal") { _, _ -> }
            .setPositiveButton("Ubah") { _, _ -> updateSymptoms() }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideKeyboard()
        _binding = null
    }
}