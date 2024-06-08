package cl.jpinodev.walletarqlifecycle.feature_usuario.data.local

import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Transaction

class TransactionDataSet {
    private val transactions: MutableList<Transaction>

    init {
        transactions = createTransactionsForUser()
    }
    fun createTransactionsForUser(): MutableList<Transaction>{
         return mutableListOf(
             Transaction("T01", 2000.0,"User01","User02", "2021-02-23 12:14:11" ),
             Transaction("T02", 2500.0,"User01","User03", "2023-04-13 02:32:55" ),
             Transaction("T03", 2800.0,"User01","User05", "2020-05-05 16:54:22" ),
             Transaction("T04", 8000.0,"User04","User01", "2010-09-25 15:34:51" ),
             Transaction("T05", 2550.0,"User03","User01", "2018-10-01 14:32:32" ),
             Transaction("T06", 2000.0,"User02","User01", "2020-12-08 13:21:10" ),
             Transaction("T07", 800.0,"User01","User04", "2022-11-10 12:14:11" ),
             Transaction("T08", 80.0,"User03","User06", "2022-11-10 12:14:11" ),
         )
        //return mutableListOf()
    }
    fun getTransactionList(): MutableList<Transaction> {
        return transactions.toMutableList() // Devuelve una copia para evitar modificaciones externas
    }
}