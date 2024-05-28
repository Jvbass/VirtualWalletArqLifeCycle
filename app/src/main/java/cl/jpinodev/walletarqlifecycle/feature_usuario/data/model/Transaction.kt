package cl.jpinodev.walletarqlifecycle.feature_usuario.data.model

data class Transaction(
    val id: String = "",
    val amount: Double = 0.0,
    val idSender: String = "",
    val idReceriver: String = "",
    val dateTime: String = ""
)
