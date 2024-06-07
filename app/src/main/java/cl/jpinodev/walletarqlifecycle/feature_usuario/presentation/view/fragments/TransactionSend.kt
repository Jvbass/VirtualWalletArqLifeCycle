package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import cl.jpinodev.walletarqlifecycle.databinding.FragmentTransactionSendBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.adapter.UsersAdapter
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.WalletViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionSend : Fragment() {
    private lateinit var binding: FragmentTransactionSendBinding
    private val viewModel: WalletViewModel by activityViewModels() //en fragment se usa by activityViewModels
    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionSendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        binding.materialToolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }
        viewModel.usuarios.observe(viewLifecycleOwner) { usuarios ->
            adapter = UsersAdapter(requireContext(), usuarios, viewModel)
            binding.spinnerSendMoney.adapter = adapter
        }
        binding.sendButton.setOnClickListener {
            val receiver = binding.spinnerSendMoney.selectedItem as Usuario
            val amount = binding.amountEditText.text.toString().toDoubleOrNull()

            if (amount != null && amount > 0) {
                val senderId = viewModel.usuarioConectado.value?.user_id
                if (senderId != null) {
                    val senderBalance = viewModel.getBalanceForUser(senderId)
                    if (senderBalance >= amount) {

                        viewModel.addTransaction(
                            senderId,
                            receiver.user_id,
                            amount,
                            getCurrentDateTime()
                        )
                        Toast.makeText(context, "Dinero enviado con éxito", Toast.LENGTH_SHORT)
                            .show()
                        navController.navigateUp()
                    }
                    else {
                        Toast.makeText(context, "Saldo insuficiente", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Ingrese un monto válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentDateTime(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return formatter.format(Date())
    }
}