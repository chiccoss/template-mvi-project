package fr.sohayb.marvelapp.app.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import fr.sohayb.marvelapp.R
import fr.sohayb.marvelapp.app.domain.AppState
import fr.sohayb.marvelapp.base.presentation.BaseActivity
import fr.sohayb.marvelapp.main.domain.MainAction

class SplashScreenActivity : BaseActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        viewModel.dispatch(MainAction.InitApplication)
    }

    override fun render(appState: AppState) {
        if(appState.mainState.goToMainActivity.consume()){
            MainActivity.start(this)
            finish()
        }
    }
}