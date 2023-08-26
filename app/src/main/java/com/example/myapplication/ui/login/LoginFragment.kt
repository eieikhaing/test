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
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

       /* val textView: TextView = binding.edtCompanyId
        loginViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        binding.btnLogin.setOnClickListener {
            if (!TextUtils.isEmpty(binding.edtCompanyId.text.toString()) && !TextUtils.isEmpty(binding.edtUserId.text) && !TextUtils.isEmpty(
                    binding.edtPassword.text
                )
            ) {
                loginViewModel.callLogin(
                    binding.edtCompanyId.text.toString(),
                    binding.edtUserId.text.toString(),
                    binding.edtPassword.text.toString()
                )
            }else{
                Toast.makeText(requireContext(),"Please fill the information",Toast.LENGTH_SHORT).show()
            }
        }
        loginViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage.isNotEmpty()) {
                requireView().findNavController()
                    .navigate(R.id.action_loginFragment_to_staffListFragment)

            }
            /* var navController = findNavController()
             navController.navigate(R.id.action_loginFragment_to_staffListFragment);*/


        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}