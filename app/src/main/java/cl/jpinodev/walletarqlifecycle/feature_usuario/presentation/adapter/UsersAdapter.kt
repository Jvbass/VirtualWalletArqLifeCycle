package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import cl.jpinodev.walletarqlifecycle.R
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.WalletViewModel

/*
* Adaptador para mostrar una lista de usuarios en un Spinner.
* Usamos BaseAdapter() para crear un adaptador base para que el Spinner sea soportado sin implementar
* todos los métodos de la interfaz SpinnerAdapter.
 */
class UsersAdapter(private val context: Context, private val usuarios: List<Usuario>, private val viewModel: WalletViewModel) :
    BaseAdapter() {

    private val filteredUsuarios: List<Usuario> = usuarios.filter {
        it.user_id != viewModel.usuarioConectado.value?.user_id
    }

    override fun getCount(): Int {
        return filteredUsuarios.size
    }

    override fun getItem(position: Int): Any {
        return filteredUsuarios[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.users_item, parent, false)
        val usuario = filteredUsuarios[position]
        val imageResource = viewModel.getUserImageResource(usuario.user_id)

        val nombreUsuario = view.findViewById<TextView>(R.id.nombreUsuario)
        val apellidoUsuario = view.findViewById<TextView>(R.id.apellidoUsuario)
        val imageUsuario = view.findViewById<ImageView>(R.id.imageUsuario)

        nombreUsuario.text = usuario.nombre
        apellidoUsuario.text = usuario.apellido
        imageUsuario.setImageResource(imageResource)

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getView(position, convertView, parent)
    }
}