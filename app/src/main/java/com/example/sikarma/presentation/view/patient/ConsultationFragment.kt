package com.example.sikarma.presentation.view.patient

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.sikarma.MainActivity
import com.example.sikarma.R
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.databinding.FragmentConsultationBinding
import com.example.sikarma.presentation.viewmodel.ConsultationViewModel
import com.google.android.material.button.MaterialButton


class ConsultationFragment : Fragment() {

    private var _binding: FragmentConsultationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ConsultationViewModel by activityViewModels()

    private lateinit var cbSymptom: CheckBox
    private lateinit var cbSymptom2: CheckBox
    private lateinit var cbSymptom3: CheckBox
    private lateinit var cbSymptom4: CheckBox
    private lateinit var cbSymptom5: CheckBox
    private lateinit var cbSymptom6: CheckBox
    private lateinit var cbSymptom7: CheckBox
    private lateinit var cbSymptom8: CheckBox
    private lateinit var cbSymptom9: CheckBox
    private lateinit var cbSymptom10: CheckBox
    private lateinit var cbSymptom11: CheckBox
    private lateinit var btnDiagnose: MaterialButton
    private lateinit var tvTypeName: TextView
    private lateinit var tvTypeDesc: TextView
    private lateinit var btnPrint: MaterialButton
    private lateinit var btnDismiss: MaterialButton

    private lateinit var listSymptoms: List<Symptoms>
    private lateinit var listRules: List<Rule>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentConsultationBinding.inflate(inflater, container, false)

        cbSymptom = binding.cbSymptom
        cbSymptom2 = binding.cbSymptom2
        cbSymptom3 = binding.cbSymptom3
        cbSymptom4 = binding.cbSymptom4
        cbSymptom5 = binding.cbSymptom5
        cbSymptom6 = binding.cbSymptom6
        cbSymptom7 = binding.cbSymptom7
        cbSymptom8 = binding.cbSymptom8
        cbSymptom9 = binding.cbSymptom9
        cbSymptom10 = binding.cbSymptom10
        cbSymptom11 = binding.cbSymptom11
        btnDiagnose = binding.btnDiagnose

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getListSymptomData.observe(viewLifecycleOwner) { listSymptoms ->
            this.listSymptoms = listSymptoms

            cbSymptom.text = listSymptoms[0].symptoms_name
            cbSymptom2.text = listSymptoms[1].symptoms_name
            cbSymptom3.text = listSymptoms[2].symptoms_name
            cbSymptom4.text = listSymptoms[3].symptoms_name
            cbSymptom5.text = listSymptoms[4].symptoms_name
            cbSymptom6.text = listSymptoms[5].symptoms_name
            cbSymptom7.text = listSymptoms[6].symptoms_name
            cbSymptom8.text = listSymptoms[7].symptoms_name
            cbSymptom9.text = listSymptoms[8].symptoms_name
            cbSymptom10.text = listSymptoms[9].symptoms_name
            cbSymptom11.text = listSymptoms[10].symptoms_name
        }

        viewModel.getListRuleData.observe(viewLifecycleOwner) { listRule ->
            this.listRules = listRule
        }

        btnDiagnose.setOnClickListener { checkRule() }
    }

    private fun checkRule() {
        if (cbSymptom.isChecked && cbSymptom2.isChecked && cbSymptom3.isChecked && cbSymptom5.isChecked && cbSymptom9.isChecked) {
            showDiagnoseResult(listRules[0].id_type, listRules[0].description)
        }

        if (cbSymptom.isChecked && cbSymptom2.isChecked && cbSymptom3.isChecked && cbSymptom5.isChecked && cbSymptom10.isChecked) {
            showDiagnoseResult(listRules[1].id_type, listRules[1].description)
        }

        if (cbSymptom.isChecked && cbSymptom2.isChecked && cbSymptom3.isChecked && cbSymptom5.isChecked && cbSymptom11.isChecked) {
            showDiagnoseResult(listRules[2].id_type, listRules[2].description)
        }

        if (cbSymptom.isChecked && cbSymptom2.isChecked && cbSymptom4.isChecked && cbSymptom5.isChecked && cbSymptom9.isChecked) {
            showDiagnoseResult(listRules[3].id_type, listRules[3].description)
        }

        if (cbSymptom.isChecked && cbSymptom2.isChecked && cbSymptom4.isChecked && cbSymptom5.isChecked && cbSymptom10.isChecked) {
            showDiagnoseResult(listRules[4].id_type, listRules[4].description)
        }

        if (cbSymptom.isChecked && cbSymptom2.isChecked && cbSymptom4.isChecked && cbSymptom5.isChecked && cbSymptom11.isChecked) {
            showDiagnoseResult(listRules[5].id_type, listRules[5].description)
        }

        if (cbSymptom3.isChecked && cbSymptom6.isChecked && cbSymptom7.isChecked && cbSymptom8.isChecked && cbSymptom9.isChecked) {
            showDiagnoseResult(listRules[6].id_type, listRules[6].description)
        }

        if (cbSymptom3.isChecked && cbSymptom6.isChecked && cbSymptom7.isChecked && cbSymptom8.isChecked && cbSymptom10.isChecked) {
            showDiagnoseResult(listRules[7].id_type, listRules[7].description)
        }

        if (cbSymptom3.isChecked && cbSymptom6.isChecked && cbSymptom7.isChecked && cbSymptom8.isChecked && cbSymptom11.isChecked) {
            showDiagnoseResult(listRules[8].id_type, listRules[8].description)
        }

        if (cbSymptom.isChecked && cbSymptom2.isChecked && cbSymptom3.isChecked && cbSymptom4.isChecked && cbSymptom5.isChecked && cbSymptom6.isChecked && cbSymptom7.isChecked && cbSymptom8.isChecked) {
            showDiagnoseResult(listRules[9].id_type, listRules[9].description)
        }
    }

    private fun showDiagnoseResult(result: String, desc: String) {
        val dialog = Dialog(requireContext())
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(R.layout.custom_dialog)
            setCancelable(false)

            tvTypeName = this.findViewById(R.id.diagnose_result)
            tvTypeName.text = result

            tvTypeDesc = this.findViewById(R.id.diagnose_desc)
            tvTypeDesc.text = desc

            btnPrint = this.findViewById(R.id.btn_print)
            btnPrint.setOnClickListener {
                Toast.makeText(requireContext(), "Berhasil print", Toast.LENGTH_SHORT).show()
            }

            btnDismiss = this.findViewById(R.id.btn_dismiss)
            btnDismiss.setOnClickListener { goToMainActivity() }
        }.show()
    }

    private fun goToMainActivity() {
        activity?.let {
            val intent = Intent(it, MainActivity::class.java)
            it.apply {
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}