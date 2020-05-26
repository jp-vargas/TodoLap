package co.lap.todolap.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import co.lap.todolap.R
import co.lap.todolap.presentation.home.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
         Handler().postDelayed({
             val intent = Intent(this, MainActivity::class.java)
             startActivity(intent)
             finish()
         }, 1000)
    }
}
