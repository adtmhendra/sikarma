package com.example.sikarma.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sikarma.R
import com.example.sikarma.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeFragment = this@HomeFragment
        }
        return binding.root
    }

    fun goToLoginFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }

    fun goToPatientTypeFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_patientTypeFragment)
    }

    fun goToConsultationFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_consultationFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}