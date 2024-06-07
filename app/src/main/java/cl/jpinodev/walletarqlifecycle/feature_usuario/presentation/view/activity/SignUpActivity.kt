package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cl.jpinodev.walletarqlifecycle.databinding.ActivitySignUpBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.utils.ToastUtils
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.WalletViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val walletViewModel: WalletViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linkLogin.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
        // boton crear cuenta
        binding.crearCuentaBtn.setOnClickListener {
            val nombre = binding.nombreEditText.text.toString()
            val apellido = binding.apellidoEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val contrasena = binding.passwordEditText.text.toString()
            val contrasenaConfirm = binding.passwordRepeatEditText.text.toString()

            //validando campos
            if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
                ToastUtils.showCustomToast(this, "Todos los campos son obligatorios")

            } else if (contrasenaConfirm != contrasena) {
                ToastUtils.showCustomToast(this, "contraseñas no coinciden")
            } else {
                val nuevoUsuario =
                    walletViewModel.crearNuevoUsuario(nombre, apellido, email, contrasena)
                ToastUtils.showCustomToast(this, "Usuario ${nuevoUsuario.nombre} creado con éxito")

                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
            }
        }
    }
}