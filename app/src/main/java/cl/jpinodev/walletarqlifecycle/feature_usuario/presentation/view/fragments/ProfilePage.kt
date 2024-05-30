package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import cl.jpinodev.walletarqlifecycle.R
import cl.jpinodev.walletarqlifecycle.databinding.FragmentProfilePageBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.WalletViewModel

class ProfilePage : Fragment() {
    private lateinit var binding: FragmentProfilePageBinding
    private val viewModel : WalletViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfilePageBinding.bind(view)
        viewModel.usuarioConectado.observe(viewLifecycleOwner) {
            binding.userName.text = it.nombre + " " + it.apellido
            val imageResource = viewModel.getUserImageResource(it.user_id)
            binding.userAvatar.setImageResource(imageResource)

        }
    }
}