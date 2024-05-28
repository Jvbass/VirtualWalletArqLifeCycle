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

        viewModel.usuarioConectado.observe(viewLifecycleOwner) {
            binding.greetingName.text = it.nombre
        }

        viewModel.transactionsLD.observe(viewLifecycleOwner) { transactions ->
            adapter.transactions = transactions
            adapter.notifyDataSetChanged()
            if (transactions.isEmpty()) {
                binding.recyclerTransactionList.visibility = View.GONE
                binding.emptyTransactionList.visibility = View.VISIBLE
            } else {
                binding.recyclerTransactionList.visibility = View.VISIBLE
                binding.emptyTransactionList.visibility = View.GONE
            }
        }
    }
 }

