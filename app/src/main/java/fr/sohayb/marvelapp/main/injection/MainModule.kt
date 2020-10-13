package fr.sohayb.marvelapp.main.injection

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import fr.sohayb.marvelapp.app.presentation.AppViewModel
import fr.sohayb.marvelapp.main.data.MainRepository
import fr.sohayb.marvelapp.main.domain.MainProcessor
import fr.sohayb.marvelapp.main.domain.MainReducer
import fr.sohayb.marvelapp.main.presentation.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MainModule = module {

    single { MainRepository(get(),get(),get()) }

    single { MainProcessor(get(),get()) }

    single { MainReducer() }

    factory { (owner: ViewModelStoreOwner) ->
        ViewModelProvider(owner, MainViewModel.Factory(get()))
            .get(MainViewModel::class.java)
    }
    viewModel { AppViewModel(get()) }

}