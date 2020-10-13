package fr.sohayb.marvelapp.app.injection

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

import fr.sohayb.marvelapp.app.domain.AppController
import fr.sohayb.marvelapp.app.domain.AppProcessor
import fr.sohayb.marvelapp.app.domain.AppReducer
import fr.sohayb.marvelapp.app.domain.AppState
import fr.sohayb.marvelapp.app.presentation.AppViewModel
import fr.sohayb.marvelapp.main.domain.MainProcessor

import org.koin.dsl.module

val AppModule = module {

    single { AppController(get(), get(), get()) }

    single { AppProcessor(get()) }

    single { AppState() }

    factory { (owner: ViewModelStoreOwner) ->
        ViewModelProvider(owner, AppViewModel.Factory(get()))
            .get(AppViewModel::class.java)
    }

    // region Processors

    single {
        listOf(
            get<MainProcessor>()
        )
    }

    // endregion Processors

    // region Reducers

    single {
        AppReducer(
            mainReducer = get(),
            initialState = get()
        )
    }

    // endregion Reducers
}