package com.example.sikarma.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sikarma.R
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.databinding.ListRuleCheckBoxBinding

class SymptomNameListAdapter(
    private val context: Context,
    private val listSymptom: List<Symptoms?>,
) :
    RecyclerView.Adapter<SymptomNameListAdapter.SymptomNameViewHolder>() {

    private val selectedItem = mutableListOf<String>()

    inner class SymptomNameViewHolder(private val binding: ListRuleCheckBoxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(symptoms: Symptoms?) {
            binding.cbSymptomName.text = context.getString(R.string.label_symptoms_code_and_name,
                symptoms?.symptoms_code,
                symptoms?.symptoms_name)
        }

        val cbSymptomsName = binding.cbSymptomName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomNameViewHolder =
        SymptomNameViewHolder(ListRuleCheckBoxBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(
        holder: SymptomNameListAdapter.SymptomNameViewHolder,
        position: Int,
    ) {
        val symptom = listSymptom[position]
        holder.bind(symptom)
        holder.cbSymptomsName.setOnClickListener {
            if (holder.cbSymptomsName.isChecked) {
                selectedItem.add(symptom!!.symptoms_name)
            } else {
                selectedItem.remove(symptom!!.symptoms_name)
            }
        }
    }

    override fun getItemCount() = listSymptom.size

    fun getSelectedItem() = selectedItem
}