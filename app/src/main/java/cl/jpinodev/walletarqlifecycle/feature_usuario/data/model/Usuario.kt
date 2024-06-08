package cl.jpinodev.walletarqlifecycle.feature_usuario.data.model

import android.os.Parcel
import android.os.Parcelable
/*
* Implementa parcelable:
* Parcel es contenedor para comunicar datos entre actividades.
* para hacer una clase parcelable, se implementan métodos requeridos para escribir y leer los datos del objeto hacia y desde un Parcel.
* El parcel puede contener múltiples datos de diferentes tipos y no está limitado a una sola transacción
* */
data class Usuario(
    val user_id: String ,
    val nombre: String ,
    val apellido: String ,
    val email: String ,
    val contrasena: String,
    val fecha_creacion: String
) : Parcelable {
    // constructor para crear un usuario desde un parcel
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )
    // metodo para escribir los datos del usuario en un Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(user_id)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(email)
        parcel.writeString(contrasena)
        parcel.writeString(fecha_creacion)
    }
    // para describir tipos especiales dentro del contendido del parcel
    override fun describeContents(): Int {
        return 0
    }
    // companion object que implementa el creador para el usario parcelable
    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }
        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}
