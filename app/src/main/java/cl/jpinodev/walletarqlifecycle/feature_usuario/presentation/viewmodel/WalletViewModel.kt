package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel

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

/*
 * ViewModel para manejar los datos y operaciones relacionadas con usuarios, transacciones y cuentas.
 *
* @property usuarios LiveData que expone la lista mutable de usuarios.
* @property transactionsLD LiveData que expone la lista mutable de transacciones.
* @property accountsLD LiveData que expone la lista mutable de cuentas.
* @property usuarioConectado LiveData que expone el usuario conectado.
* @property _usuarios MutableLiveData que almacena la lista mutable de usuarios.
* @property _transactions MutableLiveData que almacena la lista mutable de transacciones.
* @property _accounts MutableLiveData que almacena la lista mutable de cuentas.
* @property _usuarioConectado MutableLiveData que almacena el usuario conectado.
*
*/
class WalletViewModel : ViewModel() {

    /*
    * Variables privadas para usuarios, transacciones y cuentas
    * */
    private val _usuarios = MutableLiveData<MutableList<Usuario>>()
    private val _transactions = MutableLiveData<MutableList<Transaction>>()
    private val _accounts = MutableLiveData<MutableList<Account>>()

    /*
    * LiveData para obtener los usuarios, usuario conectado, transacciones y cuentas
    * */
    val usuarios: LiveData<MutableList<Usuario>> get() = _usuarios
    val transactionsLD: MutableLiveData<MutableList<Transaction>> get() = _transactions
    val accountsLD: MutableLiveData<MutableList<Account>> get() = _accounts
    val usuarioConectado: LiveData<Usuario> get() = _usuarioConectado

    private val _usuarioConectado = MutableLiveData<Usuario>()

    /*
    *  Inicializa los usuarios, las transacciones y las cuentas
    * */
    init {
        _usuarios.value =  UsuariosDataSet().getAllUsuarios()
        _transactions.value = TransactionDataSet().getTransactionList()
        _accounts.value = AccountDataSet().getAllAccounts()
    }

    /*
    * Metodo para definir el usuario conectado
    * @Params usuario: Usuario
    * @UsedBy autenticarUsuario, MainContainer
    * */
    fun setUsuarioConectado(usuario: Usuario) { //Setea el usuario conectado
        _usuarioConectado.value = usuario
    }

    /*
    * Metodo para controlar acceso a la aplicación
    * @Params userId: String, password: String
    * @UsedBy LoginPageFragment
    * */
    fun autenticarUsuario(email: String, password: String): Usuario? {
        val usuario = _usuarios.value?.find { it.email == email && it.contrasena == password }
        if (usuario != null) {
            setUsuarioConectado(usuario)
            return usuario
        } else {
            return null
        }
    }
    /*
    *  Metodo que encarga de obtener el recurso de imagen correspondiente al usuario que recibe
    * @Params userId: String
    * @UsedBy HomePageFragment, TransactionViewHolder y ProfilePage
    * */
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

    /*
    * Metodo para obtener el saldo de un usuario
    * @Params userId: String
    * @UsedBy HomePageFragment
    * */
    fun getBalanceForUser(userId: String): Double {
        return _accounts.value?.find { it.user_id == userId }?.balance ?: 0.0
    }
}