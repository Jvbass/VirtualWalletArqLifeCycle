package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cl.jpinodev.walletarqlifecycle.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}