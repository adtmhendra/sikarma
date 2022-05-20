package com.example.sikarma.presentation.view.admin.rule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sikarma.data.entity.Type
import com.example.sikarma.databinding.FragmentRuleDataBinding
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

        binding.fab.setOnClickListener { goToAddRuleDataFragment() }

//        viewModel.getRuleAndType().observe(viewLifecycleOwner) {
//            Log.d("RuleDataFragment", it[1].toString())
//        }
    }

    private fun goToAddRuleDataFragment() {
        findNavController().navigate(RuleDataFragmentDirections.actionRuleDataFragmentToAddRuleDataFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}