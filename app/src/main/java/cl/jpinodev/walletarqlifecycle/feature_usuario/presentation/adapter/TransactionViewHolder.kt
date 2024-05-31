package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import cl.jpinodev.walletarqlifecycle.R
import cl.jpinodev.walletarqlifecycle.databinding.TransactionItemBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Transaction
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.WalletViewModel
/*
* Se encarga de pintar en el RecyclerView la información de la transacción.
* Se realiza un filtro para obtener solo las transacciones pertenecientes al usuario conectado.
* Define al otherUserId como el id del usuario con el que se realizó la transacción para obtener
* su información y pintarla en el RecyclerView. Ademas define si el usuario conectado recibe o enviando dinero.
* con esto definimos el icono y el tipo de operación.
*
*  @Params bindingItem: TransactionItemBinding, usuarios: List<Usuario>
*  */
class TransactionViewHolder(
    private var bindingItem: TransactionItemBinding,
    private val usuarios: List<Usuario>
) : RecyclerView.ViewHolder(bindingItem.root) {

    fun bind(transaction: Transaction, usuarioConectado: Usuario?) {
        with(transaction) {
            bindingItem.transactionAmount.text = transaction.amount.toString()
            bindingItem.transactionDate.text = transaction.dateTime

            // Obtener el id del otro usuario
            val otherUserId = if (transaction.idSender == usuarioConectado?.user_id) {
                transaction.idReceriver
            } else {
                transaction.idSender
            }

            // Obtener la información del otro usuario y pintarla en el RecyclerView
            val otherUser = usuarios.find { it.user_id == otherUserId }
            otherUser?.let { user ->
                bindingItem.transactionUserName.text = user.nombre
                bindingItem.transactionUserLastname.text = user.apellido
                val imageResource = WalletViewModel().getUserImageResource(user.user_id)
                bindingItem.transactionUserImg.setImageResource(imageResource)
            }
            // Definir si el usuario conectado recibe o envía dinero
            if (transaction.idSender == usuarioConectado?.user_id) {
                // Enviando
                bindingItem.operationIcon.setImageResource(R.drawable.send_icon_yellow)
                bindingItem.transactionOperationType.text = "-"
            } else {
                // Recibiendo
                bindingItem.operationIcon.setImageResource(R.drawable.request_icon_blue)
                bindingItem.transactionOperationType.text = "+"
            }
        }
    }
}