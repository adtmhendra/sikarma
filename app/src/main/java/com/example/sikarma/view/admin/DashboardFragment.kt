package com.example.sikarma.view.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sikarma.R
import com.example.sikarma.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        goToSymptomsDataFragment()
        goToTypeDataFragment()
        goToRuleDataFragment()

        return binding.root
    }

    private fun goToSymptomsDataFragment() {
        binding.button.setOnClickListener { findNavController().navigate(R.id.action_dashboardFragment_to_symptomsDataFragment) }
    }

    private fun goToTypeDataFragment() {
        binding.button2.setOnClickListener { findNavController().navigate(R.id.action_dashboardFragment_to_typeDataFragment) }
    }

    private fun goToRuleDataFragment() {
        binding.button3.setOnClickListener { findNavController().navigate(R.id.action_dashboardFragment_to_ruleDataFragment) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}