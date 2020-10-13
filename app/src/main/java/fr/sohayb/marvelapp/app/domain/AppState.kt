package fr.sohayb.marvelapp.app.domain

import fr.sohayb.marvelapp.base.domain.StateType
import fr.sohayb.marvelapp.main.domain.MainState

data class AppState(
    val mainState: MainState = MainState()
) : StateType