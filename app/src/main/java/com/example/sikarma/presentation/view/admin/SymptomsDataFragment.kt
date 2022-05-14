package com.example.sikarma.presentation.view.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sikarma.R
import com.example.sikarma.databinding.FragmentSymptomsDataBinding
import com.example.sikarma.presentation.adapter.SymptomsDataFragmentAdapter
import com.example.sikarma.presentation.viewmodel.SymptomsViewModel

class SymptomsDataFragment : Fragment() {

    private var _binding: FragmentSymptomsDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SymptomsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSymptomsDataBinding.inflate(inflater, container, false)

        binding.rvSymptomsData.setHasFixedSize(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getListSymptomsData()

        binding.fab.setOnClickListener { goToAddSymptomsDataFragment() }
    }

    private fun getListSymptomsData() {
        val adapter = SymptomsDataFragmentAdapter {
            viewModel.deleteSymptoms(it)
        }

        binding.rvSymptomsData.apply {
            layoutManager = LinearLayoutManager(this@SymptomsDataFragment.context)
            this.adapter = adapter
        }

        viewModel.getListSymptomsData.observe(viewLifecycleOwner) { listSymptoms ->
            listSymptoms.let { adapter.submitList(listSymptoms) }
        }
    }

    private fun goToAddSymptomsDataFragment() {
        findNavController().navigate(R.id.action_symptomsDataFragment_to_addSymptomsDataFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}