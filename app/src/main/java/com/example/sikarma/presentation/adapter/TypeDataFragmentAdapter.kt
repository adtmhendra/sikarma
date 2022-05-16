package com.example.sikarma.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sikarma.data.entity.Type
import com.example.sikarma.databinding.ListTypeBinding

class TypeDataFragmentAdapter(private val onItemClicked: (Type) -> Unit) :
    ListAdapter<Type, TypeDataFragmentAdapter.TypeDataViewHolder>(DiffCallback) {

    class TypeDataViewHolder(private val binding: ListTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(type: Type) {
            binding.apply {
                tvTypeCode.text = type.type_code
                tvTypeName.text = type.type_name
            }
        }

        val materialCardView = binding.materialListType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeDataViewHolder =
        TypeDataViewHolder(ListTypeBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: TypeDataViewHolder, position: Int) {
        val type = getItem(position)
        holder.apply {
            bind(type)
            materialCardView.setOnClickListener { onItemClicked(type) }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Type>() {
            override fun areItemsTheSame(oldItem: Type, newItem: Type) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Type, newItem: Type): Boolean =
                oldItem.type_code == newItem.type_code
        }
    }
}