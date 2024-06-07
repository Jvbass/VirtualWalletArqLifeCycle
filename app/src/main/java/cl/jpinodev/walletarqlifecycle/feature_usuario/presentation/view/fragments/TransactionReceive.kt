package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import cl.jpinodev.walletarqlifecycle.databinding.FragmentTransactionReceiveBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.adapter.UsersAdapter
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.WalletViewModel

class TransactionReceive : Fragment() {
    private lateinit var binding: FragmentTransactionReceiveBinding
    private val viewModel: WalletViewModel by activityViewModels()
    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionReceiveBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        binding.materialToolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }

         viewModel.usuarioConectado.observe(viewLifecycleOwner) {
            val imageResource = viewModel.getUserImageResource(it.user_id)
            binding.nombreUsuario.text = it.nombre
            binding.apellidoUsuario.text = it.apellido
            binding.imageUsuario.setImageResource(imageResource)
        }
    }
}