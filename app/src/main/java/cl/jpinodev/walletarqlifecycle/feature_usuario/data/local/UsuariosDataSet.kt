package cl.jpinodev.walletarqlifecycle.feature_usuario.data.local

import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario

class UsuariosDataSet {
    fun createUsuarios(): MutableList<Usuario> {
        return mutableListOf(
            Usuario("User01", "Juan", "123", "123", "14-10-2021"),
            Usuario("User02", "Pedro", "pedro@admin", "123123", "14-10-2021"),
            Usuario("User03", "Maria", "maria@admin", "123123", "14-10-2021"),
            Usuario("User04", "Jose", "jose@admin", "123123", "14-10-2021"),
            Usuario("User05", "Luis", "luis@admin", "123123", "14-10-2021"),
            Usuario("User06", "Ana", "ana@admin", "123123", "14-10-2021"),
        )
    }
    fun getAllUsuarios(): MutableList<Usuario>? {
        return createUsuarios()
    }
}