package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario

class UserViewModel: ViewModel() {
    private val _usuarios = MutableLiveData<MutableList<Usuario>>()
    val usuarios: LiveData<MutableList<Usuario>> get() = _usuarios

    /*
    fun setUsuario(usuario: Usuario) {
        _usuarios.value = usuario
    }
    */
  init {
      _usuarios.value = mutableListOf(Usuario("User01", "Juan", "123",
          "123",  "14-10-2021"))
  }

    private val _usuarioConectado = MutableLiveData<Usuario>()
    val usuarioConectado: LiveData<Usuario> get() = _usuarioConectado

    fun setUsuarioConectado(usuario: Usuario) { //Setea el usuario conectado
        _usuarioConectado.value = usuario
    }
    fun autenticarUsuario(email: String, password: String): Usuario? {
        return _usuarios.value?.find { it.email == email && it.contrasena == password } //devuelve el usuario donde email y contrasena coinsidan
    }
 }