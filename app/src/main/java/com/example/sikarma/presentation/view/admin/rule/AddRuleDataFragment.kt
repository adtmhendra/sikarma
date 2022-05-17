package com.example.sikarma.presentation.view.admin.rule

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sikarma.databinding.FragmentAddRuleDataBinding
import com.example.sikarma.presentation.adapter.SymptomNameListAdapter

class AddRuleDataFragment : Fragment() {

    private var _binding: FragmentAddRuleDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddRuleDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val symptoms = listOf<Symptom>(
            Symptom("batuk berdahak"),
            Symptom("batuk kering"),
            Symptom("keringat dingin"),
            Symptom("panas"))

        val adapter = SymptomNameListAdapter(symptoms)

        binding.rvListSymptomName.setHasFixedSize(true)
        binding.rvListSymptomName.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvListSymptomName.adapter = adapter
    }

    // Disable save button when symptoms edit text is empty
    private val addSymptomsTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
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

data class Symptom(
    val symptomName: String,
)