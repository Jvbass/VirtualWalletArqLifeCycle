package cl.jpinodev.walletarqlifecycle.feature_usuario.data.local

import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario

class UsuariosDataSet {
    private fun createUsuarios(): MutableList<Usuario> {
        return mutableListOf(
            Usuario("User01", "Juan","Pino", "j@pino", "123", "14-10-2021"),
            Usuario("User02", "Dulce ","Loynaz", "dulce@admin", "123123", "13-11-2021"),
            Usuario("User03", "Rigoberta", "Menchú", "rigoberta@admin", "123123", "14-09-2023"),
            Usuario("User04", "Fidelina ", "Gamyi", "fidelina@admin", "123123", "06-05-2011"),
            Usuario("User05", "Clarice", "Lispector", "clarice@admin", "123123", "15-03-2019"),
            Usuario("User06", "Ana","Corpse", "ana@admin", "123123", "14-10-2021"),
            Usuario("User07", "Rosario","Castellanos", "rosario@admin", "123123", "13-11-2021"),
            Usuario("User08", "Pedro", "Pedrope", "pedrope@admin", "123123", "14-09-2023"),

        )
    }
    fun getAllUsuarios(): MutableList<Usuario>? {
        return createUsuarios()
    }
}