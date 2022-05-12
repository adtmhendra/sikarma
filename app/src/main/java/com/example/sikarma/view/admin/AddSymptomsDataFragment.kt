package com.example.sikarma.view.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sikarma.R
import com.example.sikarma.databinding.FragmentAddSymptomsDataBinding

class AddSymptomsDataFragment : Fragment() {

    private var _binding: FragmentAddSymptomsDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddSymptomsDataBinding.inflate(inflater, container, false)

        goToSymptomsDataFragment()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun goToSymptomsDataFragment() {
        binding.btnSave.setOnClickListener {
            findNavController().navigate(R.id.action_addSymptomsDataFragment_to_symptomsDataFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}