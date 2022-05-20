package com.example.sikarma.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sikarma.R
import com.example.sikarma.data.entity.Type
import com.example.sikarma.presentation.contract.Layout
import com.google.android.material.card.MaterialCardView

class TypeDataFragmentAdapter(private val layout: Int, private val onItemClicked: (Type) -> Unit) :
    ListAdapter<Type, TypeDataFragmentAdapter.TypeDataViewHolder>(DiffCallback) {

    class TypeDataViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {

        val tvTypeCode = view?.findViewById<TextView>(R.id.tv_type_code)
        val tvTypeName = view?.findViewById<TextView>(R.id.tv_type_name)
        val materialCardView = view?.findViewById<MaterialCardView>(R.id.material_list_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeDataViewHolder {

        val layout = when (layout) {
            Layout.ADMIN -> R.layout.list_type
            else -> R.layout.list_type_patient
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return TypeDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypeDataViewHolder, position: Int) {
        val type = getItem(position)

        holder.apply {
            tvTypeCode?.text = type.type_code
            tvTypeName?.text = type.type_name

            materialCardView?.setOnClickListener { onItemClicked(type) }
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