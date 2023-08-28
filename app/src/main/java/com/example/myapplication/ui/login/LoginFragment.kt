package com.example.myapplication.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.btnLogin.setOnClickListener {
            if (!TextUtils.isEmpty(binding.edtCompanyId.text.toString()) && !TextUtils.isEmpty(
                    binding.edtUserId.text
                ) && !TextUtils.isEmpty(
                    binding.edtPassword.text
                )
            ) {
                loginViewModel.callLogin(
                    binding.edtCompanyId.text.toString(),
                    binding.edtUserId.text.toString(),
                    binding.edtPassword.text.toString()
                )
            } else {
                Toast.makeText(requireContext(), "Please fill the information", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        loginViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if(errorMessage != null){
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }

        }

        loginViewModel.employeeDetailsData.observe(viewLifecycleOwner) { employeeDetailData ->
            if (employeeDetailData != null) {

                val bundle = Bundle().apply {
                    putParcelable("employeeDetailsData", employeeDetailData)
                }
                requireView().findNavController()
                    .navigate(R.id.action_loginFragment_to_staffListFragment, bundle)

            }
        }
        loginViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        loginViewModel.employeeDetailsData.value = null
        loginViewModel.errorMessage.value = null
    }
}