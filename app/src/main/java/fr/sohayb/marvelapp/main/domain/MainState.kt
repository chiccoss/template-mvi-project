package fr.sohayb.marvelapp.main.domain

import fr.sohayb.marvelapp.base.domain.StateType
import fr.sohayb.marvelapp.base.util.BooleanOneTimeEvent

data class MainState(
    //For
    val goToMainActivity : BooleanOneTimeEvent = BooleanOneTimeEvent(false)


) : StateType