package fr.sohayb.marvelapp.main.data

import android.content.Context
import fr.sohayb.marvelapp.app.api.Resource
import fr.sohayb.marvelapp.app.data.NetworkDataSource
import fr.sohayb.marvelapp.app.data.PreferencesDataSource

class MainRepository(
    private val preferencesDataSource: PreferencesDataSource,
    private val networkDataSource: NetworkDataSource,
    private val context: Context
) {


   /* suspend fun getAllCharacters(): Resource<QuranApiResponse> {
        //val forgotPasswordData = ForgotPasswordData(email)

        return networkDataSource.getAyahtInSura()

    }*/




}