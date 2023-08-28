package com.example.myapplication.ui.stafflist

import EmployeeDetailsData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentStaffListBinding
import com.example.myapplication.ui.login.LoginViewModel

class StaffListFragment : Fragment() {

    private var _binding: FragmentStaffListBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        _binding = FragmentStaffListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val employeeDetailsData = arguments?.getParcelable<EmployeeDetailsData>("employeeDetailsData")
        var items = employeeDetailsData?.Items
        var groups = employeeDetailsData?.Groups
        val adapter = StaffAdapter(items ?: emptyList())
        binding.rvStaffList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStaffList.adapter = adapter
        adapter.notifyDataSetChanged()


        val personalDataAdapter = PersonalDataAdapter(groups ?: emptyList())
        binding.rvPersonalData.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPersonalData.adapter = personalDataAdapter
        adapter.notifyDataSetChanged()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}