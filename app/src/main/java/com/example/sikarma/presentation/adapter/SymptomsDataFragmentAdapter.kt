package com.example.sikarma.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sikarma.data.entity.Symptoms
import com.example.sikarma.databinding.ListSymptomsBinding

class SymptomsDataFragmentAdapter :
    ListAdapter<Symptoms, SymptomsDataFragmentAdapter.SymptomsDataFragmentViewHolder>(DiffCallback) {

    inner class SymptomsDataFragmentViewHolder(private val binding: ListSymptomsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(symptoms: Symptoms) {
            binding.tvSymptomName.text = symptoms.symptoms
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SymptomsDataFragmentViewHolder =
        SymptomsDataFragmentViewHolder(ListSymptomsBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: SymptomsDataFragmentViewHolder, position: Int) {
        val symptom = getItem(position)
        holder.bind(symptom)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Symptoms>() {
            override fun areItemsTheSame(oldItem: Symptoms, newItem: Symptoms) =
                oldItem.symptoms == newItem.symptoms

            override fun areContentsTheSame(oldItem: Symptoms, newItem: Symptoms) =
                oldItem.symptoms == newItem.symptoms
        }
    }
}