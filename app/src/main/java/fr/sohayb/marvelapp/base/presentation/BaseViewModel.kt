package fr.sohayb.marvelapp.base.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.sohayb.marvelapp.app.domain.AppController
import fr.sohayb.marvelapp.app.domain.AppState
import fr.sohayb.marvelapp.base.domain.ActionType

abstract class BaseViewModel(private val appController: AppController) : ViewModel() {

    val state: LiveData<AppState> = appController.state

    fun dispatch(action: ActionType) {
        appController.dispatch(action)
    }
}

abstract class BaseViewModel2(
    application: Application,
    private val appController: AppController
): AndroidViewModel(application) {

    val state: LiveData<AppState> = appController.state

    fun dispatch(action: ActionType) {
        appController.dispatch(action)
    }


}