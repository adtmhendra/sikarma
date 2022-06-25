package com.example.sikarma.presentation.view.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.sikarma.databinding.FragmentDetailPatientTypeBinding
import com.example.sikarma.presentation.viewmodel.TypeViewModel

class DetailPatientTypeFragment : Fragment() {

    private var _binding: FragmentDetailPatientTypeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TypeViewModel by activityViewModels()

    private val navigationArgs: DetailPatientTypeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailPatientTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getItemDetail(navigationArgs.idType)
    }

    private fun getItemDetail(id: Int) {
        viewModel.retrieveType(id).observe(this.viewLifecycleOwner) {
            binding.apply {
                tvTypeName.text = it.type_name
                tvTypeDescription.text = it.type_desc
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}