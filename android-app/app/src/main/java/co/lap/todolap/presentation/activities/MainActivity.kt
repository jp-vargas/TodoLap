package co.lap.todolap.presentation.activities

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import co.lap.todolap.R
import co.lap.todolap.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configNav()
    }

    private fun configNav() {
        NavigationUI.setupWithNavController(
            bnvMenu,
            Navigation.findNavController(this, R.id.fragContent)
        )
    }
}
