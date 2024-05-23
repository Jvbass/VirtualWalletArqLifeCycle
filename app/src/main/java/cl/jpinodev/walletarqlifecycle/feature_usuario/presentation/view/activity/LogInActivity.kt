package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import cl.jpinodev.walletarqlifecycle.R
import cl.jpinodev.walletarqlifecycle.databinding.ActivityLogInBinding
import cl.jpinodev.walletarqlifecycle.databinding.ActivitySplashBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.fragments.HomePageFragment
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.UserViewModel

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val txtEmail = binding.emailEditText.text.toString()
        val txtPassword = binding.passwordEditText.text.toString()


        userViewModel.usuarios.observe(this, Observer {
            binding.loginButton.setOnClickListener {
                val usuario = userViewModel.autenticarUsuario(txtEmail, txtPassword)
                Log.d("usuario", usuario.toString())
                if (usuario != null) {
                    userViewModel.setUsuarioConectado(usuario)
                    /* val intent = Intent(this, MainContainer::class.java)
                     startActivity(intent)*/
                }
            }
            Log.d("usuarios", it.toString())
        })
    }
}