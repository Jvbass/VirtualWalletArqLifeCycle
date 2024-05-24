package cl.jpinodev.walletarqlifecycle.feature_usuario.data.model

import android.os.Parcel
import android.os.Parcelable

data class Usuario(
    val user_id: String ,
    val nombre: String ,
    val email: String ,
    val contrasena: String,
    val fecha_creacion: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(user_id)
        parcel.writeString(nombre)
        parcel.writeString(email)
        parcel.writeString(contrasena)
        parcel.writeString(fecha_creacion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}
