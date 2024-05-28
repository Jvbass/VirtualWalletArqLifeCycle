package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import cl.jpinodev.walletarqlifecycle.databinding.TransactionItemBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Transaction

class TransactionViewHolder(private var bindingItem: TransactionItemBinding): RecyclerView.ViewHolder(bindingItem.root) {
    fun bind (transaction: Transaction) {
        with(transaction) {
            bindingItem.transactionAmount.text = transaction.amount.toString()
        }
    }
}