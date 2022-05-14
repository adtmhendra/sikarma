package com.example.sikarma.presentation.contract

import com.example.sikarma.data.entity.Symptoms

interface OnClickListener {
    fun onUpdate(symptoms: Symptoms)
    fun onDelete(symptoms: Symptoms)
}