package fr.sohayb.marvelapp.app.domain

import fr.sohayb.marvelapp.base.domain.ReducerType
import fr.sohayb.marvelapp.base.domain.ResultType
import fr.sohayb.marvelapp.main.domain.MainReducer

class AppReducer(
    private val mainReducer: MainReducer,
    private val initialState: AppState
) : ReducerType<AppState> {

    override fun reduce(result: ResultType, state: AppState): AppState {

        return when(result) {

            is AppResult.ResetAppState -> initialState

            else -> AppState(
                mainState = mainReducer.reduce(result, state.mainState)
            )
        }
    }
}