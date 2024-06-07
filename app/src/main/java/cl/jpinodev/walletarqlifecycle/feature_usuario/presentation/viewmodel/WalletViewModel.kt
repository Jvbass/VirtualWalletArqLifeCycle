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
            else -> R.drawable.pp_standar // Imagen predeterminada en caso de no encontrar coincidencia
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
    // Métodos para agregar transacciones
    fun addTransaction(senderId: String, receiverId: String, amount: Double, dateTime: String) {
        val newTransaction = Transaction(
            id = generateTransactionId(),
            amount = amount,
            idSender = senderId,
            idReceriver = receiverId,
            dateTime = dateTime
        )
        _transactions.value?.add(newTransaction)
        updateBalances(senderId, receiverId, amount)
    }

    /*
    * Metodo para generar un id de transacción
    * @UsedBy addTransaction
    * */
    private fun generateTransactionId(): String {
        return "T${_transactions.value?.size?.plus(1)}"
    }

    /***********
    *  Metodo para actualizar los saldos de los usuarios
    * ************/
    private fun updateBalances(senderId: String, receiverId: String, amount: Double) {
        val accounts = _accounts.value ?: return // Obtener la lista de cuentas
        val senderAccount = accounts.find { it.user_id == senderId } // Encontrar la cuenta del remitente
        val receiverAccount = accounts.find { it.user_id == receiverId } // Encontrar la cuenta del receptor

        /*
        *  Actualizar los saldos de los usuarios
        * */
        senderAccount?.let { // Con let se verifica que la cuenta no sea nula
            it.balance -= amount // resta saldo del remitente
        }

        receiverAccount?.let {
            it.balance += amount // suma saldo al receptor
        }

        _accounts.value = accounts
    }// Metodo para obtener las transacciones de un usuario

}// end of class WalletViewModel