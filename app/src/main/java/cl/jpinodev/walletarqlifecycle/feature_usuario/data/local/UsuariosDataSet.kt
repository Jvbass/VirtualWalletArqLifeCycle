package cl.jpinodev.walletarqlifecycle.feature_usuario.data.local

import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario

class UsuariosDataSet {
    private fun createUsuarios(): MutableList<Usuario> {
        return mutableListOf(
            Usuario("User01", "Juan","Pino", "j@pino", "123", "14-10-2021"),
            Usuario("User02", "Petronila ","Eizaguirre", "pedro@admin", "123123", "13-11-2021"),
            Usuario("User03", "Esmeralda ", "Brandibuque", "maria@admin", "123123", "14-09-2023"),
            Usuario("User04", "Fidelina ", "Gamyi", "jose@admin", "123123", "06-05-2011"),
            Usuario("User05", "Daniela", "Hermosilla", "luis@admin", "123123", "15-03-2019"),
            Usuario("User06", "Ana","Corpse", "ana@admin", "123", "14-10-2021"),
        )
    }
    fun getAllUsuarios(): MutableList<Usuario>? {
        return createUsuarios()
    }
}