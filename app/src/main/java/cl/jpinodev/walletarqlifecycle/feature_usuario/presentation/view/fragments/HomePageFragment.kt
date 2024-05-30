package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cl.jpinodev.walletarqlifecycle.R
import cl.jpinodev.walletarqlifecycle.databinding.FragmentHomePageBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.adapter.TransactionAdapter
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.WalletViewModel

class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private val viewModel: WalletViewModel by activityViewModels()
    private lateinit var adapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomePageBinding.bind(view)
        adapter = TransactionAdapter()
        val navController = Navigation.findNavController(view)

        binding.recyclerTransactionList.adapter = adapter
        binding.recyclerTransactionList.layoutManager = LinearLayoutManager(this.context)


        binding.profileImage.setOnClickListener {
            navController.navigate(R.id.profilePage)
        }
        binding.btnRequest.setOnClickListener {
            navController.navigate(R.id.transactionReceive)
        }
        binding.btnSend.setOnClickListener {
            navController.navigate(R.id.transactionSend)
        }

        viewModel.usuarioConectado.observe(viewLifecycleOwner) { usuario ->
            binding.greetingName.text = usuario.nombre
            val imageResource = viewModel.getUserImageResource(usuario.user_id)
            binding.profileImage.setImageResource(imageResource)
            filterTransactions(usuario.user_id)
            binding.balanceAmount.text = viewModel.getBalanceForUser(usuario.user_id).toString()
            filterTransactions(usuario.user_id)
        }

        viewModel.transactionsLD.observe(viewLifecycleOwner) { transactions ->
            val usuarioId = viewModel.usuarioConectado.value?.user_id
            if (usuarioId != null) {
                filterTransactions(usuarioId)
            }
        }
    }

    private fun filterTransactions(userId: String) {
        val allTransactions = viewModel.transactionsLD.value ?: mutableListOf()
        val filteredTransactions = allTransactions.filter {
            it.idSender == userId || it.idReceriver == userId
        }
        adapter.transactions = filteredTransactions.toMutableList()
        adapter.notifyDataSetChanged()

        if (filteredTransactions.isEmpty()) {
            binding.recyclerTransactionList.visibility = View.GONE
            binding.emptyTransactionList.visibility = View.VISIBLE
        } else {
            binding.recyclerTransactionList.visibility = View.VISIBLE
            binding.emptyTransactionList.visibility = View.GONE
        }
    }
}

