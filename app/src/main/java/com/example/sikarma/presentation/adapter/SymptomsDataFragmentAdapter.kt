package com.example.sikarma.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sikarma.R
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.databinding.ListSymptomsBinding
import com.example.sikarma.presentation.contract.OnClickListener

class SymptomsDataFragmentAdapter(var listener: OnClickListener) :
    ListAdapter<Symptoms, SymptomsDataFragmentAdapter.SymptomsDataFragmentViewHolder>(DiffCallback) {

    class SymptomsDataFragmentViewHolder(private val binding: ListSymptomsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(symptoms: Symptoms) {
            binding.tvSymptomCode.text = symptoms.symptoms_code
            binding.tvSymptomName.text = symptoms.symptoms_name
        }

        val btnDeleteSymptoms: ImageButton = itemView.findViewById(R.id.img_btn_delete)
        val btnUpdateSymptoms: ImageButton = itemView.findViewById(R.id.img_btn_update)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SymptomsDataFragmentViewHolder =
        SymptomsDataFragmentViewHolder(ListSymptomsBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: SymptomsDataFragmentViewHolder, position: Int) {
        val symptom = getItem(position)

        holder.apply {
            bind(symptom)

            btnUpdateSymptoms.setOnClickListener { listener.onUpdate(symptom) }
            btnDeleteSymptoms.setOnClickListener { listener.onDelete(symptom) }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Symptoms>() {
            override fun areItemsTheSame(oldItem: Symptoms, newItem: Symptoms) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Symptoms, newItem: Symptoms) =
                oldItem.symptoms_name == newItem.symptoms_name
        }
    }
}