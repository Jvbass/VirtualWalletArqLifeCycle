package cl.jpinodev.walletarqlifecycle.feature_usuario.data.local

import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Transaccion

class TransaccionDataSet {
    fun createTransactionsForUser(userId: String): MutableList<Transaccion>{
        return mutableListOf(
            Transaccion("T01", 2000.0,"User01","User02"),
            Transaccion("T02", 2500.0,"User01","User03"),
            Transaccion("T03", 2800.0,"User01","User04"),
            Transaccion("T04", 8000.0,"User04","User01"),
            Transaccion("T05", 2550.0,"User03","User01"),
            Transaccion("T06", 2000.0,"User02","User01"),
        )
    }
}