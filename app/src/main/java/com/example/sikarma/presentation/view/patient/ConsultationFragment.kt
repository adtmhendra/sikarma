package com.example.sikarma.presentation.view.patient

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sikarma.MainActivity
import com.example.sikarma.R
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.databinding.FragmentConsultationBinding
import com.example.sikarma.presentation.viewmodel.ConsultationViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConsultationFragment : Fragment() {

    private var _binding: FragmentConsultationBinding? = null
    private val binding get() = _binding!!

    // Deklarasi ViewModel konsultasi
    private val viewModel: ConsultationViewModel by activityViewModels()

    // Deklarasi lambat view group
    private lateinit var edtName: TextInputEditText
    private lateinit var edtAge: TextInputEditText
    private lateinit var acTvGender: AutoCompleteTextView
    private lateinit var btnStart: MaterialButton
    private lateinit var btnCancel: MaterialButton
    private lateinit var patientName: String
    private lateinit var patientAge: String
    private lateinit var currentDate: String
    private lateinit var currentGender: String
    private lateinit var currentTime: String

    // Deklarasi lambat data gejala dan data rule
    private lateinit var symptoms: List<Symptoms>
    private lateinit var rules: List<Rule>

    private var currentQuestion = 0
    private val checkSymptoms = mutableListOf<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentConsultationBinding.inflate(inflater, container, false)

        // Mendapatkan data tanggal dan waktu terkini
        val current = LocalDateTime.now()
        val date = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val time = DateTimeFormatter.ofPattern("HH:mm:ss")
        currentDate = current.format(date)
        currentTime = current.format(time)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showDialog()

        // Mengakses properti getSymptoms pada ViewModel konsultasi
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

        // Mengakses properti getRules pada ViewModel konsultasi
        viewModel.getRules.observe(viewLifecycleOwner) { rules ->
            this.rules = rules
        }
    }

    // Pindah pertanyaan gejala
    private fun goToNextQuestion() {
        currentQuestion++
        // Kondisi jika pertanyaan saat ini < total pertanyaan gejala
        if (currentQuestion < symptoms.size) {
            binding.tvQuestion.text = getString(R.string.label_consultation_question,
                symptoms[currentQuestion].symptoms_code,
                symptoms[currentQuestion].symptoms_name)
        } else showResult()
    }

    // Penerapan algoritma forward chaining
    private fun showResult() {
        if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[2] && checkSymptoms[4] && checkSymptoms[8]) {
            showDiagnosticResult(rules[0].id_type, rules[0].description)
        } else if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[2] && checkSymptoms[4] && checkSymptoms[9]) {
            showDiagnosticResult(rules[1].id_type, rules[1].description)
        } else if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[2] && checkSymptoms[4] && checkSymptoms[10]) {
            showDiagnosticResult(rules[2].id_type, rules[2].description)
        } else if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[3] && checkSymptoms[4] && checkSymptoms[8]) {
            showDiagnosticResult(rules[3].id_type, rules[3].description)
        } else if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[3] && checkSymptoms[4] && checkSymptoms[9]) {
            showDiagnosticResult(rules[4].id_type, rules[4].description)
        } else if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[3] && checkSymptoms[4] && checkSymptoms[10]) {
            showDiagnosticResult(rules[5].id_type, rules[5].description)
        } else if (checkSymptoms[2] && checkSymptoms[5] && checkSymptoms[6] && checkSymptoms[7] && checkSymptoms[8]) {
            showDiagnosticResult(rules[6].id_type, rules[6].description)
        } else if (checkSymptoms[2] && checkSymptoms[5] && checkSymptoms[6] && checkSymptoms[7] && checkSymptoms[9]) {
            showDiagnosticResult(rules[7].id_type, rules[7].description)
        } else if (checkSymptoms[2] && checkSymptoms[5] && checkSymptoms[6] && checkSymptoms[7] && checkSymptoms[10]) {
            showDiagnosticResult(rules[8].id_type, rules[8].description)
        } else if (checkSymptoms[0] && checkSymptoms[1] && checkSymptoms[2] && checkSymptoms[3] && checkSymptoms[4] && checkSymptoms[5] && checkSymptoms[6] && checkSymptoms[7]) {
            showDiagnosticResult(rules[9].id_type, rules[9].description)
        } else {
            showDiagnosticResult(getString(R.string.label_no_result), "-")
        }
    }

    // Berpindah ke halaman Hasil Diagnosa dengan membawa data hasil diagnosa
    private fun showDiagnosticResult(
        result: String? = getString(R.string.label_undefined),
        desc: String? = getString(R.string.label_undefined),
    ) {
        findNavController().navigate(ConsultationFragmentDirections.actionConsultationFragmentToDiagnosticResultFragment(
            name = patientName,
            age = patientAge,
            gender = currentGender,
            date = currentDate,
            time = currentTime,
            type = result,
            desc = desc,
        ))
    }

    // Menampilkan dialog form konsultasi
    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(R.layout.custom_dialog)
            setCancelable(false)

            edtName = this.findViewById(R.id.edt_name)
            edtAge = this.findViewById(R.id.edt_age)
            btnStart = this.findViewById(R.id.btn_start)
            btnCancel = this.findViewById(R.id.btn_cancel)
            acTvGender = this.findViewById(R.id.ac_tv_gender)

            setUpGender()

            btnStart.setOnClickListener {
                patientName = edtName.text.toString()
                patientAge = edtAge.text.toString()
                getGenderData()
                dismiss()
            }

            btnCancel.setOnClickListener { goToMainActivity() }

            edtName.addTextChangedListener(inputPatientDataTextWatcher)
            edtAge.addTextChangedListener(inputPatientDataTextWatcher)
        }.show()
    }

    // Mengatur list gender ke dalam combo box
    private fun setUpGender() {
        val gender = listOf(getString(R.string.label_male), getString(R.string.label_female))
        (acTvGender as? AutoCompleteTextView)?.setAdapter(ArrayAdapter(
            requireContext(),
            R.layout.list_type_dropdown,
            R.id.tv_dropdown,
            gender
        ))

        getGenderData()
    }

    // Mendapatkan data gender yang dipilih oleh user
    private fun getGenderData() {
        acTvGender.setOnItemClickListener { adapterView, _, i, _ ->
            currentGender = adapterView.getItemAtPosition(i).toString()
        }
    }

    private val inputPatientDataTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            btnStart.isEnabled =
                edtName.text!!.isNotEmpty() && edtAge.text!!.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    // Pindah ke halaman utama aplikasi
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