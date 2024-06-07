package cl.jpinodev.walletarqlifecycle.feature_usuario.data.model

data class Account (
    val cuenta_id: String = "",
    val user_id: String = "",
    var balance: Double = 0.0
)