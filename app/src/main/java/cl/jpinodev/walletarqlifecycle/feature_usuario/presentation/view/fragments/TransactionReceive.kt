package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import cl.jpinodev.walletarqlifecycle.databinding.FragmentTransactionReceiveBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.utils.ToastUtils
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.WalletViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionReceive : Fragment() {
    private lateinit var binding: FragmentTransactionReceiveBinding
    private val viewModel: WalletViewModel by activityViewModels()
    // private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.buttonReceive.setOnClickListener {
            val amount = binding.txtAmountSend.text.toString().toDoubleOrNull()
            val connectedUserId = viewModel.usuarioConectado.value?.user_id
            if (amount != null && amount > 0) {
                if (connectedUserId != null) {
                    viewModel.receiveMoney(connectedUserId, amount)
                    viewModel.addTransactionDeposit(
                        connectedUserId,
                        amount,
                        getCurrentDateTime()
                    )
                }
                ToastUtils.showCustomToast(requireContext(), "Deposito realizado con exito")
                navController.navigateUp()
//                binding.txtAmountSend.text?.clear()
            } else {
                ToastUtils.showCustomToast(requireContext(), "Ingrese un monto valido valido")
            }
        }
    }

    private fun getCurrentDateTime(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return formatter.format(Date())
    }
}