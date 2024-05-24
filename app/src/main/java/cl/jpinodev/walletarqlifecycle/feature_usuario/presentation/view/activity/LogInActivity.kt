package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import cl.jpinodev.walletarqlifecycle.databinding.ActivityLogInBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.UserViewModel

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.loginButton.setOnClickListener {
            val txtEmail = binding.emailEditText.text.toString()
            val txtPassword = binding.passwordEditText.text.toString()

            val usuario = userViewModel.autenticarUsuario(txtEmail, txtPassword)
            Log.i("usuario", usuario.toString())
            if (usuario != null) {
                userViewModel.setUsuarioConectado(usuario)
                val intent = Intent(this, MainContainer::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos, por favor intente nuevamente", Toast.LENGTH_SHORT).show()
            }
        }
    }
}