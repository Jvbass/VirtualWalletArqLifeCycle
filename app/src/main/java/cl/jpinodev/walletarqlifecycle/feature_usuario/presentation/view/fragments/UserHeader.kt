package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import cl.jpinodev.walletarqlifecycle.R
import cl.jpinodev.walletarqlifecycle.databinding.FragmentUserHeaderBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.UserViewModel

class UserHeader : Fragment() {
    private lateinit var binding: FragmentUserHeaderBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserHeaderBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.usuarios.observe(viewLifecycleOwner) {

            //binding.greetingName.text = it.value
            Log.d("UserHeader", it.toString())
        }
    }
}