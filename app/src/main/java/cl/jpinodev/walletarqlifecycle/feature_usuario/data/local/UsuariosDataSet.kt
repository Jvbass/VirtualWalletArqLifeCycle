package cl.jpinodev.walletarqlifecycle.feature_usuario.data.local

import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario

class UsuariosDataSet {
    fun createUsuarios(): MutableList<Usuario> {
        return mutableListOf(
            Usuario("User01", "Juan","Pino", "123", "123", "14-10-2021"),
            Usuario("User02", "Pedro","Pe", "pedro@admin", "123123", "14-10-2021"),
            Usuario("User03", "Maria", "Alvarez", "maria@admin", "123123", "14-10-2021"),
            Usuario("User04", "Jose", "Perez", "jose@admin", "123123", "14-10-2021"),
            Usuario("User05", "Luis", "Hermosilla", "luis@admin", "123123", "14-10-2021"),
            Usuario("User06", "Ana","Corpse", "ana@admin", "123123", "14-10-2021"),
        )
    }
    fun getAllUsuarios(): MutableList<Usuario>? {
        return createUsuarios()
    }
}