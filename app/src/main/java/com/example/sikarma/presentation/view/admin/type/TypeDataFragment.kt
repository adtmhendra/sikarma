package com.example.sikarma.presentation.view.admin.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sikarma.databinding.FragmentTypeDataBinding
import com.example.sikarma.presentation.adapter.TypeDataFragmentAdapter
import com.example.sikarma.presentation.viewmodel.TypeViewModel

class TypeDataFragment : Fragment() {

    private var _binding: FragmentTypeDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TypeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTypeDataBinding.inflate(inflater, container, false)

        binding.rvTypeData.setHasFixedSize(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getListTypeData()

        binding.fab.setOnClickListener { goToAddTypeDataFragment() }
    }

    private fun getListTypeData() {
        val adapter = TypeDataFragmentAdapter {
            findNavController().navigate(TypeDataFragmentDirections.actionTypeDataFragmentToDetailTypeDataFragment(
                idType = it.id_type
            ))
        }

        binding.rvTypeData.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }

        viewModel.getListTypeData.observe(this.viewLifecycleOwner) { listType ->
            listType.let { adapter.submitList(listType) }
        }
    }

    private fun goToAddTypeDataFragment() {
        findNavController().navigate(TypeDataFragmentDirections.actionTypeDataFragmentToAddTypeDataFragment(
            title = "Tambah Jenis Penyakit"
        ))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}