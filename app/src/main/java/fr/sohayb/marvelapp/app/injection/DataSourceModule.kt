package fr.sohayb.marvelapp.app.injection


import fr.sohayb.marvelapp.app.data.NetworkDataSource
import fr.sohayb.marvelapp.app.data.PreferencesDataSource
import fr.sohayb.marvelapp.app.database.RoomDataSource
import org.koin.dsl.module

val DataSourceModule = module {



    single {
        RoomDataSource()
    }


    single {
        PreferencesDataSource(get())
    }


    single {
        NetworkDataSource(get(),get())
    }

}