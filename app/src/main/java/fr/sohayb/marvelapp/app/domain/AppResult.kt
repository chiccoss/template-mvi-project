package fr.sohayb.marvelapp.app.domain

import fr.sohayb.marvelapp.base.domain.ResultType

sealed class AppResult: ResultType {

    object ResetAppState: AppResult()

}