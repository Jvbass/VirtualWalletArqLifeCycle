package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cl.jpinodev.walletarqlifecycle.databinding.ActivityMainContainerBinding
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.local.DataHolder
import cl.jpinodev.walletarqlifecycle.feature_usuario.data.model.Usuario
import cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.viewmodel.WalletViewModel

class MainContainer : AppCompatActivity() {
    private lateinit var binding: ActivityMainContainerBinding
    private val viewModel: WalletViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("usuario")){
            //val usuario = DataHolder.currentUser
            val usuario = intent.getParcelableExtra<Usuario>("usuario")

            if (usuario != null){
                viewModel.setUsuarioConectado(usuario)
                Log.d("MainContainer", "Usuario conectado: $usuario")
            }
        }
    }
}