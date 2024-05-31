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
/**
* Fragmento que representa la página de inicio, mostrando información del usuario conectado,
* su saldo en cuenta y las transacciones realizadas relacionadas a él.
* Se encarga de inicializar y configurar la vista, manejar la navegación y observar los
* cambios en los datos desde el ViewModel relacionado.
*
* */
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
        val navController = Navigation.findNavController(view)

        binding.profileImage.setOnClickListener {
            navController.navigate(R.id.profilePage)
        }
        binding.btnRequest.setOnClickListener {
            navController.navigate(R.id.transactionReceive)
        }
        binding.btnSend.setOnClickListener {
            navController.navigate(R.id.transactionSend)
        }
/*
*  Observa los cambios en el usuario conectado y actualiza la vista con la información del usuario.
* Tambien inicializa el adapter con el usuario conectado y la lista de usuarios.
* */
        viewModel.usuarioConectado.observe(viewLifecycleOwner) { usuario ->
            binding.greetingName.text = usuario.nombre
            val imageResource = viewModel.getUserImageResource(usuario.user_id)
            binding.profileImage.setImageResource(imageResource)
            binding.balanceAmount.text = viewModel.getBalanceForUser(usuario.user_id).toString()

            // Inicializar el adapter después de que usuarioConectado esté disponible
            adapter = TransactionAdapter(usuario, viewModel.usuarios.value ?: mutableListOf())
            binding.recyclerTransactionList.adapter = adapter
            binding.recyclerTransactionList.layoutManager = LinearLayoutManager(this.context)

            filterTransactions(usuario.user_id)
        }
/*
*  Observa los cambios en la lista de transacciones y llama el metodo para filtrar las transacciones
* relacionadas con el usuario conectado.
* */
        viewModel.transactionsLD.observe(viewLifecycleOwner) { transactions ->
            val usuarioId = viewModel.usuarioConectado.value?.user_id
            if (usuarioId != null) {
                filterTransactions(usuarioId)
            }
        }
    }

    /**
     * Filtra las transacciones relacionadas con el usuario conectado y actualiza la lista de
     * transacciones.
     *
     * @param userId ID del usuario conectado.
     */
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
