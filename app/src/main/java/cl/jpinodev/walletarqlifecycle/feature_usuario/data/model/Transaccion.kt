package cl.jpinodev.walletarqlifecycle.feature_usuario.data.model

data class Transaccion(
    val id: String = "",
    val monto: Double = 0.0,
    val idSender: String = "",
    val idReceriver: String = ""
)
