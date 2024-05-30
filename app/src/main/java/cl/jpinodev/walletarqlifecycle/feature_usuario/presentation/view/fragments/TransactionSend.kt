package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import cl.jpinodev.walletarqlifecycle.databinding.FragmentTransactionSendBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.adapter.UsersAdapter
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.WalletViewModel

class TransactionSend : Fragment() {
    private lateinit var binding: FragmentTransactionSendBinding
    private val viewModel: WalletViewModel by activityViewModels()
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
            adapter = UsersAdapter(requireContext(), usuarios)
            binding.spinnerSendMoney.adapter = adapter
        }
    }
}