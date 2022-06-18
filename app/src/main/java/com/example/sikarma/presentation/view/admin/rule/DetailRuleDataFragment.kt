package com.example.sikarma.presentation.view.admin.rule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.databinding.FragmentDetailRuleDataBinding
import com.example.sikarma.presentation.viewmodel.RuleViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DetailRuleDataFragment : Fragment() {

    private var _binding: FragmentDetailRuleDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RuleViewModel by activityViewModels()

    private val navigationArgs: DetailRuleDataFragmentArgs by navArgs()

    private lateinit var rule: Rule

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailRuleDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getItemDetail(navigationArgs.idRule)

        binding.btnDelete.setOnClickListener { showConfirmationDialog(rule) }
        binding.btnUpdate.setOnClickListener { }
    }

    private fun deleteItem(rule: Rule) {
        viewModel.deleteRule(rule)
        findNavController().navigate(DetailRuleDataFragmentDirections.actionDetailRuleDataFragmentToRuleDataFragment())
    }

    private fun getItemDetail(id: Int) {
        viewModel.retrieveRule(id).observe(this.viewLifecycleOwner) {
            rule = it
            binding.apply {
                tvRuleCode.text = it.rule_code
                tvTypeName.text = it.id_type
                tvSymptomName.text = it.id_symptoms
            }
        }
    }

    private fun showConfirmationDialog(rule: Rule) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Yakin ingin menghapus?")
            .setMessage("Kode rule : ${rule.rule_code}\n" +
                    "Nama penyakit : ${rule.id_type}\n\n")
            .setCancelable(false)
            .setNegativeButton("Batal") { _, _ -> }
            .setPositiveButton("Hapus") { _, _ ->
                deleteItem(rule)
                Toast.makeText(requireContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}