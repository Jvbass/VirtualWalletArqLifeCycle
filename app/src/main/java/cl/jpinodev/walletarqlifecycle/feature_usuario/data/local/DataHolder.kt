package cl.jpinodev.walletarqlifecycle.feature_usuario.data.local

import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Account
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Transaction
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario

/*
*  Clase singleton para guardar datos en memoria para que persistan entre activites
* */
object DataHolder {
    var transactions: MutableList<Transaction> = TransactionDataSet().getTransactionList()
    var accounts: MutableList<Account> = AccountDataSet().getAllAccounts() ?: mutableListOf()
    var usuarios: MutableList<Usuario> = UsuariosDataSet().getAllUsuarios() ?: mutableListOf()
}