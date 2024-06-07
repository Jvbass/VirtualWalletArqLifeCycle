package cl.jpinodev.walletarqlifecycle.feature_usuario.data.local

import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Account

class AccountDataSet {
    private fun createAccounts(): MutableList<Account> {
        return mutableListOf(
            Account("acc1", "User01", 100.0),
            Account("acc2", "User02", 200.0),
            Account("acc3", "User03", 300.0),
            Account("acc4", "User04", 400.0),
            Account("acc5", "User05", 500.0),
            Account("acc6", "User06", 600.0),
            Account("acc7", "User07", 700.0),
            Account("acc8", "User08", 800.0),
        )
    }
    fun getAllAccounts(): MutableList<Account>? {
        return createAccounts()
    }
}