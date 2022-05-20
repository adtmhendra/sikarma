package com.example.sikarma.presentation.view.patient

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sikarma.databinding.FragmentPatientTypeBinding
import com.example.sikarma.presentation.adapter.TypeDataFragmentAdapter
import com.example.sikarma.presentation.viewmodel.TypeViewModel

class PatientTypeFragment : Fragment() {

    private var _binding: FragmentPatientTypeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TypeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPatientTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListTypeRecyclerView()
    }

    private fun setListTypeRecyclerView() {
        val adapter = TypeDataFragmentAdapter(2) {
            findNavController().navigate(PatientTypeFragmentDirections.actionPatientTypeFragmentToDetailPatientTypeFragment(
                idType = it.id_type
            ))
        }

        binding.apply {
            rvListType.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvListType.adapter = adapter
        }

        viewModel.getListTypeData.observe(this.viewLifecycleOwner) { listType ->
            listType.let { adapter.submitList(listType) }
            Log.d("PatientTypeFragment", listType.map { it.type_name }.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}