package devin.astra.pokeapi.presentation.screens.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import devin.astra.pokeapi.R
import devin.astra.pokeapi.databinding.ActivitySplashBinding
import devin.astra.pokeapi.presentation.screens.home.HomeActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

  private lateinit var binding: ActivitySplashBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivitySplashBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnStart.setOnClickListener { navigateToHome() }
  }

  private fun navigateToHome() {
    val intent = HomeActivity.newIntent(this)
    finish()
    startActivity(intent)
  }
}