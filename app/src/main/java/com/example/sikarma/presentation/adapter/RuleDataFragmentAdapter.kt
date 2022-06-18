package com.example.sikarma.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sikarma.R
import com.example.sikarma.data.entity.Rule
import com.example.sikarma.databinding.ListRuleBinding
import com.google.android.material.card.MaterialCardView

class RuleDataFragmentAdapter(private val onItemClicked: (Rule) -> Unit) :
    ListAdapter<Rule, RuleDataFragmentAdapter.RuleDataFragmentViewHolder>(DiffCallback) {

    class RuleDataFragmentViewHolder(private val binding: ListRuleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rule: Rule) {
            binding.apply {
                tvRuleCode.text = rule.rule_code
                tvTypeName.text = rule.id_type
                tvSymptomName.text = rule.id_symptoms
            }
        }

        val materialCardView =
            binding.materialListRule.findViewById<MaterialCardView>(R.id.material_list_rule)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuleDataFragmentViewHolder =
        RuleDataFragmentViewHolder(ListRuleBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: RuleDataFragmentViewHolder, position: Int) {
        val rule = getItem(position)

        holder.apply {
            bind(rule)
            materialCardView.setOnClickListener { onItemClicked(rule) }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Rule>() {

            override fun areItemsTheSame(oldItem: Rule, newItem: Rule): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Rule, newItem: Rule): Boolean =
                oldItem.id_type == newItem.id_type
        }
    }
}