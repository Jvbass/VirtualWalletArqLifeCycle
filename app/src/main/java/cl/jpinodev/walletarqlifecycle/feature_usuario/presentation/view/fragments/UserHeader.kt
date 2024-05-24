package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import cl.jpinodev.walletarqlifecycle.R
import cl.jpinodev.walletarqlifecycle.databinding.FragmentUserHeaderBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.UserViewModel

class UserHeader : Fragment() {
    private lateinit var binding: FragmentUserHeaderBinding
    private val viewModel: UserViewModel by activityViewModels() // en fragments se usa by activityViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserHeaderBinding.inflate(inflater, container, false)
        Log.e("UserHeader", "estoy en onCreateView")
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserHeaderBinding.bind(view)

        viewModel.usuarioConectado.observe(viewLifecycleOwner) {
            binding.greetingName.text = it.nombre
            Log.d("test desde header", "Hola desde header")
        }
    }
}