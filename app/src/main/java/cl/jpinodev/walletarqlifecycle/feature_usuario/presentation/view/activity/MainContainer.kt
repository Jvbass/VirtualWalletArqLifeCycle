package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.jpinodev.walletarqlifecycle.R
import cl.jpinodev.walletarqlifecycle.databinding.ActivityLogInBinding
import cl.jpinodev.walletarqlifecycle.databinding.ActivityMainContainerBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.fragments.HomePageFragment
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.UserViewModel

class MainContainer : AppCompatActivity() {
    private lateinit var binding: ActivityMainContainerBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("usuario")){
            val usuario = intent.getParcelableExtra<Usuario>("usuario")

            if (usuario != null){
                viewModel.setUsuarioConectado(usuario)
                Log.d("MainContainer", "Usuario conectado: $usuario")
            }
        }
    }
}