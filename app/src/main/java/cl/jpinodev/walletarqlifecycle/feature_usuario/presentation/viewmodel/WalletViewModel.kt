package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.jpinodev.walletarqlifecycle.R
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.local.AccountDataSet
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.local.TransactionDataSet
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.local.UsuariosDataSet
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Account
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Transaction
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario

class WalletViewModel : ViewModel() {
    private val _usuarios = MutableLiveData<MutableList<Usuario>>()
    private val _transactions = MutableLiveData<MutableList<Transaction>>()
    private val _accounts = MutableLiveData<MutableList<Account>>()

    val usuarios: LiveData<MutableList<Usuario>> get() = _usuarios
    val transactionsLD: MutableLiveData<MutableList<Transaction>> get() = _transactions
    val accountsLD: MutableLiveData<MutableList<Account>> get() = _accounts
    val usuarioConectado: LiveData<Usuario> get() = _usuarioConectado

    private val _usuarioConectado = MutableLiveData<Usuario>()

    init {
        _usuarios.value =  UsuariosDataSet().getAllUsuarios()
        _transactions.value = TransactionDataSet().getTransactionList()
        _accounts.value = AccountDataSet().getAllAccounts()
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
    fun getUserImageResource(userId: String): Int {
        return when (userId) {
            "User01" -> R.drawable.pp3
            "User02" -> R.drawable.pp2
            "User03" -> R.drawable.pp1
            "User04" -> R.drawable.pp4
            "User05" -> R.drawable.pp5
            else -> R.drawable.pdefault // Imagen predeterminada en caso de no encontrar coincidencia
        }
    }
    fun getBalanceForUser(userId: String): Double {
        return _accounts.value?.find { it.user_id == userId }?.balance ?: 0.0
    }
}