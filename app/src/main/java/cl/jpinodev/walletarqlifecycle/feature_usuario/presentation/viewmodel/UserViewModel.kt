package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.local.UsuariosDataSet
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario

class UserViewModel : ViewModel() {
    private val _usuarios = MutableLiveData<MutableList<Usuario>>()
    val usuarios: LiveData<MutableList<Usuario>> get() = _usuarios
    private val _usuarioConectado = MutableLiveData<Usuario>()
    val usuarioConectado: LiveData<Usuario> get() = _usuarioConectado

    /*
    fun setUsuario(usuario: Usuario) {
        _usuarios.value = usuario
    }
    */
    init {
        _usuarios.value =  UsuariosDataSet().getAllUsuarios()
        //_usuarioConectado.value = _usuarios.value?.first()
    }

    fun setUsuarioConectado(usuario: Usuario) { //Setea el usuario conectado
        _usuarioConectado.value = usuario
    }

    fun autenticarUsuario(email: String, password: String): Usuario? {
        val usuario = _usuarios.value?.find { it.email == email && it.contrasena == password }
        if (usuario != null) {
            setUsuarioConectado(usuario)
            return usuario
        } else {
            return null
        }
    }
}