package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import cl.jpinodev.walletarqlifecycle.R

 /*
* Toast personalizado con colores de la app
* */
object ToastUtils {
    fun showCustomToast(context: Context, message: String) {
        val inflater = LayoutInflater.from(context)
        val layout: View = inflater.inflate(R.layout.custom_toast, null)

        val text: TextView = layout.findViewById(R.id.toastText)
        text.text = message

        val toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }
}
