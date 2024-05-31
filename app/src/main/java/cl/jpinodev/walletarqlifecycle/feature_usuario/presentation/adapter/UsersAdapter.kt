package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import cl.jpinodev.walletarqlifecycle.R
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario

/*
* Adaptador para mostrar una lista de usuarios en un Spinner.
* Usamos BaseAdapter() para crear un adaptador base para que el Spinner sea soportado sin implementar
* todos los métodos de la interfaz SpinnerAdapter.
 */
class UsersAdapter(private val context: Context, private val usuarios: List<Usuario>) : BaseAdapter() {
    override fun getCount(): Int {
        return usuarios.size
    }
    override fun getItem(position: Int): Any {
        return usuarios[position]
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
       override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.users_item, parent, false)
        val usuario = usuarios[position]
        val nombreUsuario = view.findViewById<TextView>(R.id.nombreUsuario)
        val apellidoUsuario = view.findViewById<TextView>(R.id.apellidoUsuario)
        nombreUsuario.text = usuario.nombre
        apellidoUsuario.text = usuario.apellido
        return view
    }
      override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getView(position, convertView, parent)
    }
  }