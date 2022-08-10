package com.example.sikarma.presentation.view.patient

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sikarma.R
import com.example.sikarma.databinding.FragmentDiagnosticResultBinding

class DiagnosticResultFragment : Fragment() {

    private var _binding: FragmentDiagnosticResultBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: DiagnosticResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDiagnosticResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvName.text = getString(R.string.label_result, navigationArgs.name)
            tvAge.text = getString(R.string.label_age, navigationArgs.age)
            tvGender.text = getString(R.string.label_result, navigationArgs.gender)
            tvDate.text = getString(R.string.label_result, navigationArgs.date)
            tvTime.text = getString(R.string.label_result, navigationArgs.time)
            tvDiagnosticResult.text = navigationArgs.type
            tvDiagnosticDesc.text = navigationArgs.desc

            btnFinished.setOnClickListener { goToHomeFragment() }
            btnShare.setOnClickListener {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TITLE, getString(R.string.title_dialog_share))
                    putExtra(Intent.EXTRA_TEXT,
                        getString(R.string.title_early_diagnosis_caps) +
                                "\n\n${getString(R.string.title_full_name)} : ${navigationArgs.name}" +
                                "\n${getString(R.string.title_age)} : ${navigationArgs.age}" +
                                "\n${getString(R.string.title_gender)} : ${navigationArgs.gender}" +
                                "\n${getString(R.string.title_date)} : ${navigationArgs.date}" +
                                "\n${getString(R.string.title_time)}: ${navigationArgs.time}" +
                                "\n\nType of Asthma : ${navigationArgs.type}" +
                                "\n${getString(R.string.title_description)} : ${navigationArgs.desc}")
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }

    private fun goToHomeFragment() {
        findNavController().navigate(DiagnosticResultFragmentDirections.actionDiagnosticResultFragmentToHomeFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}