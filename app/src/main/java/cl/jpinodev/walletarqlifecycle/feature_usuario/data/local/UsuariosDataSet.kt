package cl.jpinodev.walletarqlifecycle.feature_usuario.data.local

import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario

class UsuariosDataSet {
    fun createUsuarios(): MutableList<Usuario>{
        return mutableListOf(
    Usuario("id1", "Juan", "admin", "14-10-2021"),
    Usuario("id2", "Pedro", "Pedrope", "13-02-2020"),
    Usuario("id3", "Maria", "Maria123", "12-08-2015"),
    Usuario("id4", "Jose", "Jose321", "31-09-2012"),
    Usuario("id5", "Luis", "Lucho666", "02-11-2023"),
        )
    }
}