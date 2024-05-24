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
    private val userViewModel: UserViewModel by viewModels() // en activity se usa by viewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.loginButton.setOnClickListener {
            val txtEmail = binding.emailEditText.text.toString()
            val txtPassword = binding.passwordEditText.text.toString()

            val user = userViewModel.autenticarUsuario(txtEmail,txtPassword)
            if (user != null) {
               //Log.d("TESTING", test.toString())
                val intent = Intent(this, MainContainer::class.java)
                intent.putExtra("usuario", user)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }

        }
    }
}