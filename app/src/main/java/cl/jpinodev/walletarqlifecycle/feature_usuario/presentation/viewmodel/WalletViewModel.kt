package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.local.TransactionDataSet
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.local.UsuariosDataSet
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Transaction
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario

class WalletViewModel : ViewModel() {
    private val _usuarios = MutableLiveData<MutableList<Usuario>>()
    private val _transactions = MutableLiveData<MutableList<Transaction>>()

    val usuarios: LiveData<MutableList<Usuario>> get() = _usuarios
    val transactionsLD: MutableLiveData<MutableList<Transaction>> get() = _transactions

    private val _usuarioConectado = MutableLiveData<Usuario>()
    val usuarioConectado: LiveData<Usuario> get() = _usuarioConectado

    init {
        _usuarios.value =  UsuariosDataSet().getAllUsuarios()
        _transactions.value = TransactionDataSet().getTransactionList()
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