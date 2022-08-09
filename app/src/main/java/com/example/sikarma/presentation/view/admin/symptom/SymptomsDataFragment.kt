package com.example.sikarma.presentation.view.admin.symptom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sikarma.R
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.databinding.FragmentSymptomsDataBinding
import com.example.sikarma.presentation.adapter.SymptomsDataFragmentAdapter
import com.example.sikarma.presentation.contract.OnClickListener
import com.example.sikarma.presentation.viewmodel.SymptomsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
        val adapter =
            SymptomsDataFragmentAdapter(object : OnClickListener {
                override fun onUpdate(symptoms: Symptoms) {
                    findNavController().navigate(SymptomsDataFragmentDirections.actionSymptomsDataFragmentToAddSymptomsDataFragment(
                        idSymptoms = symptoms.id_symptoms,
                        title = getString(R.string.title_edit_symptom_data)
                    ))
                }

                override fun onDelete(symptoms: Symptoms) {
                    showConfirmationDialog(symptoms)
                }
            })

        binding.rvSymptomsData.apply {
            layoutManager = LinearLayoutManager(this@SymptomsDataFragment.context)
            this.adapter = adapter
        }

        viewModel.getListSymptomsData.observe(viewLifecycleOwner) { listSymptoms ->
            listSymptoms.let { adapter.submitList(listSymptoms) }
        }
    }

    private fun goToAddSymptomsDataFragment() {
        findNavController().navigate(SymptomsDataFragmentDirections.actionSymptomsDataFragmentToAddSymptomsDataFragment(
            getString(R.string.title_add_symptom_data)))
    }

    private fun showConfirmationDialog(symptoms: Symptoms) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.label_delete_confirmation))
            .setMessage("${resources.getString(R.string.title_symptom_code)} : ${symptoms.symptoms_code}\n" +
                    "${resources.getString(R.string.title_symptom_name)} : ${symptoms.symptoms_name}")
            .setCancelable(false)
            .setNegativeButton(resources.getString(R.string.label_button_cancel)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.label_button_yes)) { _, _ ->
                deleteItem(symptoms)
                Toast.makeText(requireContext(),
                    resources.getString(R.string.label_deleted_successfully),
                    Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun deleteItem(symptoms: Symptoms) {
        viewModel.deleteSymptoms(symptoms)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}