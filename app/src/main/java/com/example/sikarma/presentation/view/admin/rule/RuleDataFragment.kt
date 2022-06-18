package com.example.sikarma.presentation.view.admin.rule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sikarma.R
import com.example.sikarma.databinding.FragmentRuleDataBinding
import com.example.sikarma.presentation.adapter.RuleDataFragmentAdapter
import com.example.sikarma.presentation.viewmodel.RuleViewModel

class RuleDataFragment : Fragment() {

    private var _binding: FragmentRuleDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RuleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRuleDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getListRuleData()

        binding.fab.setOnClickListener { goToAddRuleDataFragment() }
    }

    private fun getListRuleData() {
        val adapter = RuleDataFragmentAdapter { rule ->
            findNavController().navigate(RuleDataFragmentDirections.actionRuleDataFragmentToDetailRuleDataFragment(
                idRule = rule.id_rule
            ))
        }

        binding.rvRuleData.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }

        viewModel.getListRuleData.observe(viewLifecycleOwner) { listRule ->
            listRule.let { adapter.submitList(listRule) }
        }
    }

    private fun goToAddRuleDataFragment() {
        findNavController().navigate(RuleDataFragmentDirections.actionRuleDataFragmentToAddRuleDataFragment(
            title = getString(R.string.title_add_rule_data)
        ))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}