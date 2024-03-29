package com.example.sikarma.presentation.view.admin.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sikarma.R
import com.example.sikarma.data.entity.Type
import com.example.sikarma.databinding.FragmentDetailTypeDataBinding
import com.example.sikarma.presentation.viewmodel.TypeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DetailTypeDataFragment : Fragment() {

    private var _binding: FragmentDetailTypeDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TypeViewModel by activityViewModels()

    private val navigationArgs: DetailTypeDataFragmentArgs by navArgs()

    lateinit var type: Type

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailTypeDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getItemDetail(navigationArgs.idType)

        binding.btnDelete.setOnClickListener { showConfirmationDialog(type) }
        binding.btnUpdate.setOnClickListener { goToAddTypeDataFragment(navigationArgs.idType) }
    }

    private fun deleteItem(type: Type) {
        viewModel.deleteType(type)
        findNavController().navigate(DetailTypeDataFragmentDirections.actionDetailTypeDataFragmentToTypeDataFragment())
    }

    private fun getItemDetail(id: Int) {
        viewModel.retrieveType(id).observe(this.viewLifecycleOwner) {
            type = it
            binding.apply {
                tvTypeCode.text = it.type_code
                tvTypeName.text = it.type_name
                tvTypeDescription.text = it.type_desc
            }
        }
    }

    private fun showConfirmationDialog(type: Type) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.label_delete_confirmation))
            .setMessage("${resources.getString(R.string.title_type_code)} : ${type.type_code}\n" +
                    "${resources.getString(R.string.title_type_name)} : ${type.type_name}\n\n")
            .setCancelable(false)
            .setNegativeButton(resources.getString(R.string.label_button_cancel)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.label_button_yes)) { _, _ ->
                deleteItem(type)
                Toast.makeText(requireContext(), resources.getString(R.string.label_deleted_successfully), Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun goToAddTypeDataFragment(id: Int) {
        findNavController().navigate(DetailTypeDataFragmentDirections.actionDetailTypeDataFragmentToAddTypeDataFragment(
            idType = id,
            title = getString(R.string.title_edit_type_data)
        ))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}