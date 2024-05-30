package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.jpinodev.walletarqlifecycle.databinding.TransactionItemBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Transaction
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario


class TransactionAdapter(
    private val usuarioConectado: Usuario?,
    private val usuarios: List<Usuario>
) : RecyclerView.Adapter<TransactionViewHolder>() {

    var transactions = mutableListOf<Transaction>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

/*
*Inicializa el ViewHolder con la lista de usuarios
*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val bindingItem = TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(bindingItem, usuarios)
    }
/*
*  Inicializa el usuario conectado.
* */
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction: Transaction = transactions[position]
        holder.bind(transaction, usuarioConectado)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}