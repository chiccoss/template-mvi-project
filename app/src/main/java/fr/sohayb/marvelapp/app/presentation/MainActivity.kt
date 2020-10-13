package fr.sohayb.marvelapp.app.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import fr.sohayb.marvelapp.R
import fr.sohayb.marvelapp.app.domain.AppState
import fr.sohayb.marvelapp.base.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {



    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            ContextCompat.startActivity(context, intent, null)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        //setTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun render(appState: AppState) = Unit




}
