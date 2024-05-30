package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import cl.jpinodev.walletarqlifecycle.databinding.UsersItemBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario

class UsersViewHolder (private var bindingItem: UsersItemBinding): RecyclerView.ViewHolder(bindingItem.root){
    fun bind (usuario: Usuario) {
        with(usuario) {
            bindingItem.nombreUsuario.text = usuario.nombre
            bindingItem.apellidoUsuario.text = usuario.apellido
        }
    }
}
