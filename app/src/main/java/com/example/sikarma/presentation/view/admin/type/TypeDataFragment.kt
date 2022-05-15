package com.example.sikarma.presentation.view.admin.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sikarma.databinding.FragmentTypeDataBinding

class TypeDataFragment : Fragment() {

    private var _binding: FragmentTypeDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTypeDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            goToAddTypeDataFragment()
        }
    }

    private fun goToAddTypeDataFragment() {
        findNavController().navigate(TypeDataFragmentDirections.actionTypeDataFragmentToAddTypeDataFragment(
            title = "Tambah Data Jenis"
        ))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}