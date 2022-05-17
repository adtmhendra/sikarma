package com.example.sikarma.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sikarma.databinding.ListRuleCheckBoxBinding
import com.example.sikarma.presentation.view.admin.rule.Symptom

class SymptomNameListAdapter(private val listSymptom: List<Symptom>) :
    RecyclerView.Adapter<SymptomNameListAdapter.SymptomNameViewHolder>() {

    inner class SymptomNameViewHolder(private val binding: ListRuleCheckBoxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(symptom: Symptom) {
            binding.cbSymptomName.text = symptom.symptomName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomNameViewHolder =
        SymptomNameViewHolder(ListRuleCheckBoxBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(
        holder: SymptomNameListAdapter.SymptomNameViewHolder,
        position: Int,
    ) {
        val symptom = listSymptom[position]
        holder.bind(symptom)
    }

    override fun getItemCount() = listSymptom.size
}