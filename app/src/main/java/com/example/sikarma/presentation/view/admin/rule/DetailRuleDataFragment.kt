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
import com.example.sikarma.R
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
        binding.btnUpdate.setOnClickListener { goToAddRuleDataFragment(navigationArgs.idRule) }
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
            .setTitle(resources.getString(R.string.label_delete_confirmation))
            .setMessage("${resources.getString(R.string.title_rule_code)} : ${rule.rule_code}\n" +
                    "${resources.getString(R.string.title_type_name)} : ${rule.id_type}\n\n")
            .setCancelable(false)
            .setNegativeButton(resources.getString(R.string.label_button_cancel)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.label_button_yes)) { _, _ ->
                deleteItem(rule)
                Toast.makeText(requireContext(),
                    resources.getString(R.string.label_deleted_successfully),
                    Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun goToAddRuleDataFragment(id: Int) {
        findNavController().navigate(DetailRuleDataFragmentDirections.actionDetailRuleDataFragmentToAddRuleDataFragment(
            idRule = id,
            title = getString(R.string.title_edit_rule_data)
        ))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}