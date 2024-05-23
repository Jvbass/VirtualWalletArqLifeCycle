package cl.jpinodev.walletarqlifecycle.feature_usuario.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import cl.jpinodev.walletarqlifecycle.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 1500 // 1.5 segundos
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({//usamos handler para ejecutar un código después de un tiempo
            val intent = Intent(this, LoginSignupActivity::class.java)// Intent usamos para navegar entre actividades
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
        } // end of onCreate
} // end of class