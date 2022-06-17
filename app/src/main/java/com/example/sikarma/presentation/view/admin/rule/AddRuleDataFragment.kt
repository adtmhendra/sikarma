package com.example.sikarma.presentation.view.admin.rule

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sikarma.R
import com.example.sikarma.databinding.FragmentAddRuleDataBinding
import com.example.sikarma.presentation.adapter.SymptomNameListAdapter
import com.example.sikarma.presentation.viewmodel.RuleViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddRuleDataFragment : Fragment() {

    private var _binding: FragmentAddRuleDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RuleViewModel by activityViewModels()

    private lateinit var ruleCode: TextInputLayout
    private lateinit var acTvType: AutoCompleteTextView
    private lateinit var edtRuleCode: TextInputEditText
    private lateinit var btnSave: MaterialButton

    private lateinit var prefix: String
    private lateinit var getRuleCode: String

    private lateinit var selectedItem: MutableList<String>
    private lateinit var typeName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddRuleDataBinding.inflate(inflater, container, false)

        ruleCode = binding.tvRuleCode
        acTvType = binding.acTvType
        edtRuleCode = binding.edtRuleCode
        btnSave = binding.btnSave
        prefix = ruleCode.prefixText.toString()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtRuleCode.addTextChangedListener(addRuleTextWatcher)

        getTypesAndRules()

        getRuleWithSymptoms()

        btnSave.setOnClickListener {
            viewModel.getTypeDescription(typeName).asLiveData()
                .observe(viewLifecycleOwner) { typeDesc ->
                    Log.d("CheckDesc", typeDesc)
                    addNewRule(typeName, selectedItem.joinToString { it }, typeDesc)
                }
        }
    }

    private fun addNewRule(typeName: String, symptomName: String, description: String) {
        getRuleCode = prefix + binding.edtRuleCode.text.toString()
        if (!isDataExist(getRuleCode, binding.acTvType.text.toString().trim().lowercase())) {
            viewModel.addNewRule(typeName, symptomName, getRuleCode, description)
            Toast.makeText(activity, "Data berhasil disimpan", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_addRuleDataFragment_to_ruleDataFragment)
        } else {
            hideKeyboard()
            Snackbar.make(btnSave, "Data telah tersedia", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun getTypesAndRules() {
        viewModel.getTypesAndRules.observe(this.viewLifecycleOwner) { data ->
            (acTvType as? AutoCompleteTextView)?.setAdapter(ArrayAdapter(requireContext(),
                R.layout.list_type_dropdown,
                R.id.tv_dropdown,
                data.map { typeName ->
                    typeName.type?.type_name
                }))

            acTvType.setOnItemClickListener { adapterView, _, i, _ ->
                typeName = adapterView.getItemAtPosition(i).toString()
            }
        }
    }

    private fun getRuleWithSymptoms() {
        viewModel.getRuleWithSymptoms.observe(this.viewLifecycleOwner) { data ->
            val adapter = SymptomNameListAdapter(
                requireContext(),
                data.map { symptoms ->
                    symptoms.symptoms
                })

            binding.apply {
                rvListSymptomName.setHasFixedSize(true)
                rvListSymptomName.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                rvListSymptomName.adapter = adapter
            }

            selectedItem = adapter.getSelectedItem()
        }
    }

    private fun isDataExist(ruleCode: String, typeId: String) =
        viewModel.checkData(ruleCode, typeId)

    // Disable save button when rule code edit text is empty
    private val addRuleTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            btnSave.isEnabled = edtRuleCode.text!!.isNotEmpty()
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