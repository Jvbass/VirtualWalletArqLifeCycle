package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import cl.jpinodev.walletarqlifecycle.databinding.TransactionItemBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Transaction
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.WalletViewModel
/*
*

* */
class TransactionViewHolder(
    private var bindingItem: TransactionItemBinding,
    private val usuarios: List<Usuario>
) : RecyclerView.ViewHolder(bindingItem.root) {

    fun bind(transaction: Transaction, usuarioConectado: Usuario?) {
        with(transaction) {
            bindingItem.transactionAmount.text = transaction.amount.toString()
            bindingItem.transactionDate.text = transaction.dateTime

            val otherUserId = if (transaction.idSender == usuarioConectado?.user_id) {
                transaction.idReceriver
            } else {
                transaction.idSender
            }

            /*
            * Recibe la lista de usuarios y encuentra el otro usuario involucrado en la transacción.
            * */
            val otherUser = usuarios.find { it.user_id == otherUserId }
            otherUser?.let { user ->
                bindingItem.transactionUserName.text = user.nombre
                val imageResource = WalletViewModel().getUserImageResource(user.user_id)
                bindingItem.transactionUserImg.setImageResource(imageResource)
            }
        }
    }
}