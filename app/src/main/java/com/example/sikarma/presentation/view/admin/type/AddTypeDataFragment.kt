package com.example.sikarma.presentation.view.admin.type

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
import com.example.sikarma.data.entity.Type
import com.example.sikarma.databinding.FragmentAddTypeDataBinding
import com.example.sikarma.presentation.viewmodel.TypeViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTypeDataFragment : Fragment() {

    private var _binding: FragmentAddTypeDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TypeViewModel by activityViewModels()

    private lateinit var edtTypeCode: TextInputEditText
    private lateinit var edtTypeName: TextInputEditText
    private lateinit var edtTypeDesc: TextInputEditText
    private lateinit var typeCode: TextInputLayout
    private lateinit var btnSave: MaterialButton

    private lateinit var prefix: String
    private lateinit var getTypeCode: String

    private val navigationArgs: AddTypeDataFragmentArgs by navArgs()

    private lateinit var type: Type

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddTypeDataBinding.inflate(inflater, container, false)

        typeCode = binding.typeCode
        edtTypeCode = binding.edtTypeCode
        edtTypeName = binding.edtTypeName
        edtTypeDesc = binding.edtTypeDescription
        btnSave = binding.btnSave

        prefix = typeCode.prefixText.toString()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtTypeCode.requestFocus()

        edtTypeCode.addTextChangedListener(addTypeTextWatcher)
        edtTypeName.addTextChangedListener(addTypeTextWatcher)

        val id = navigationArgs.idType
        if (id > 0) {
            edtTypeCode.isEnabled = false
            typeCode.prefixText = ""
            viewModel.retrieveType(id).observe(this.viewLifecycleOwner) {
                type = it
                bind(type, id)
            }
        } else {
            binding.btnSave.setOnClickListener { addNewType() }
        }
    }

    private fun bind(type: Type, id: Int) {
        edtTypeCode.setText(type.type_code, TextView.BufferType.SPANNABLE)
        edtTypeName.setText(type.type_name, TextView.BufferType.SPANNABLE)
        btnSave.setOnClickListener { showConfirmationDialog(id) }
    }

    private fun addNewType() {
        getTypeCode = prefix + edtTypeCode.text.toString()
        if (!isDataExists(getTypeCode, edtTypeName.text.toString().trim().lowercase())) {
            viewModel.addNewType(
                getTypeCode,
                edtTypeName.text.toString().trim().lowercase(),
                edtTypeDesc.text.toString().trim().lowercase(),
            )
            Toast.makeText(activity,
                resources.getString(R.string.label_add_successfully),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(AddTypeDataFragmentDirections.actionAddTypeDataFragmentToTypeDataFragment())
        } else {
            hideKeyboard()
            Snackbar.make(btnSave,
                resources.getString(R.string.label_data_exist),
                Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun updateType(typeId: Int) {
        getTypeCode = edtTypeCode.text.toString()
        if (isDataExists(getTypeCode, edtTypeName.text.toString().trim().lowercase())) {
            viewModel.updateType(
                typeId,
                getTypeCode,
                edtTypeName.text.toString().trim().lowercase(),
                edtTypeDesc.text.toString().trim().lowercase(),
            )
            Toast.makeText(activity,
                resources.getString(R.string.label_updated_successfully),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(AddTypeDataFragmentDirections.actionAddTypeDataFragmentToTypeDataFragment())
        } else {
            hideKeyboard()
            Snackbar.make(btnSave,
                resources.getString(R.string.label_data_exist),
                Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun isDataExists(typeCode: String, typeName: String) =
        viewModel.checkData(typeCode, typeName)

    private val addTypeTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            btnSave.isEnabled =
                edtTypeCode.text!!.isNotEmpty() && edtTypeName.text!!.isNotEmpty()
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

    private fun showConfirmationDialog(typeId: Int) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.label_confirmation))
            .setMessage(resources.getString(R.string.label_update_confirmation))
            .setCancelable(false)
            .setNegativeButton(resources.getString(R.string.label_button_cancel)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.label_button_yes)) { _, _ ->
                updateType(typeId)
            }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideKeyboard()
        _binding = null
    }
}