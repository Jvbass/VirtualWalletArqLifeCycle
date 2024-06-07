package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cl.jpinodev.walletarqlifecycle.databinding.ActivityLogInBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.utils.ToastUtils
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.WalletViewModel

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private val walletViewModel: WalletViewModel by viewModels() // en activity se usa by viewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linkCrearCuenta.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            val txtEmail = binding.emailEditText.text.toString()
            val txtPassword = binding.passwordEditText.text.toString()

            val user = walletViewModel.autenticarUsuario(txtEmail,txtPassword)
            if (user != null) {
               //Log.d("TESTING", test.toString())
                val intent = Intent(this, MainContainer::class.java)
               // DataHolder.currentUser = user
                intent.putExtra("usuario", user)
                startActivity(intent)
            } else {
                ToastUtils.showCustomToast(this, "Usuario o contraseña incorrecto")
            }
        }
    }
}