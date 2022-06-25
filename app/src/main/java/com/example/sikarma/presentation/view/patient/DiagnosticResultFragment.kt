package com.example.sikarma.presentation.view.patient

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