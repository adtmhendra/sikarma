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

    private lateinit var tvTypeName: TextView
    private lateinit var tvTypeDesc: TextView
    private lateinit var btnPrint: MaterialButton
    private lateinit var btnDismiss: MaterialButton

    private lateinit var symptoms: List<Symptoms>
    private lateinit var rules: List<Rule>

    private var currentQuestion = 0
    private val checkSymptoms = mutableListOf<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentConsultationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSymptoms.observe(viewLifecycleOwner) { symptoms ->
            this.symptoms = symptoms

            binding.apply {
                tvQuestion.text = getString(R.string.label_consultation_question,
                    symptoms[currentQuestion].symptoms_code,
                    symptoms[currentQuestion].symptoms_name)

                btnYes.setOnClickListener {
                    checkSymptoms.add(true)
                    goToNextQuestion()
                }

                btnNo.setOnClickListener {
                    checkSymptoms.add(false)
                    goToNextQuestion()
                }
            }
        }

        viewModel.getRules.observe(viewLifecycleOwner) { rules ->
            this.rules = rules
        }
    }

    private fun goToNextQuestion() {
        currentQuestion++
        if (currentQuestion < symptoms.size) {
            binding.tvQuestion.text = getString(R.string.label_consultation_question,
                symptoms[currentQuestion].symptoms_code,
                symptoms[currentQuestion].symptoms_name)
        } else showDiagnoseResult()
    }

    private fun showDiagnoseResult() {
        if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[2] && checkSymptoms[4] && checkSymptoms[8]) {
            showDialog(rules[0].id_type, rules[0].description)
        } else if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[2] && checkSymptoms[4] && checkSymptoms[9]) {
            showDialog(rules[1].id_type, rules[1].description)
        } else if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[2] && checkSymptoms[4] && checkSymptoms[10]) {
            showDialog(rules[2].id_type, rules[2].description)
        } else if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[3] && checkSymptoms[4] && checkSymptoms[8]) {
            showDialog(rules[3].id_type, rules[3].description)
        } else if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[3] && checkSymptoms[4] && checkSymptoms[9]) {
            showDialog(rules[4].id_type, rules[4].description)
        } else if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[3] && checkSymptoms[4] && checkSymptoms[10]) {
            showDialog(rules[5].id_type, rules[5].description)
        } else if (checkSymptoms[2] && checkSymptoms[5] && checkSymptoms[6] && checkSymptoms[7] && checkSymptoms[8]) {
            showDialog(rules[6].id_type, rules[6].description)
        } else if (checkSymptoms[2] && checkSymptoms[5] && checkSymptoms[6] && checkSymptoms[7] && checkSymptoms[9]) {
            showDialog(rules[7].id_type, rules[7].description)
        } else if (checkSymptoms[2] && checkSymptoms[5] && checkSymptoms[6] && checkSymptoms[7] && checkSymptoms[10]) {
            showDialog(rules[8].id_type, rules[8].description)
        } else if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[2] && checkSymptoms[3] && checkSymptoms[4] && checkSymptoms[5] && checkSymptoms[6] && checkSymptoms[7]) {
            showDialog(rules[9].id_type, rules[9].description)
        } else {
            showDialog("Penyakit tidak ditemukan", "-")
        }
    }

    private fun showDialog(result: String, desc: String) {
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
                Toast.makeText(requireContext(), "Hasil diagnosa dicetak", Toast.LENGTH_SHORT)
                    .show()
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