package cl.jpinodev.walletarqlifecycle.feature_usuario.data.model

data class Usuario(
    val user_id: String ,
    val nombre: String ,
    val email: String ,
    val contrasena: String,
    val fecha_creacion: String
)
