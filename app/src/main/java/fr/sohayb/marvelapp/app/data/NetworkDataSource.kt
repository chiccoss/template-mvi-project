package fr.sohayb.marvelapp.app.data

import android.content.Context
import fr.sohayb.marvelapp.app.api.NetworkService
import fr.sohayb.marvelapp.app.api.Resource
import fr.sohayb.marvelapp.app.api.ResponseHandler

class NetworkDataSource(private val networkService: NetworkService, context: Context) : ResponseHandler(context) {


    // TEST OF GETCARS AND ADDCAR
/*

    suspend fun getAyahTafseer(tafseerId : Int,suraNumber: Int,ayahNumber: Int): Resource<VerseTafseer> {
        return try {
            handleSuccess(networkService.getAyahTafseer(tafseerId,suraNumber,ayahNumber).toLocalVerseTafseer())
        } catch (e: Exception) {
            handleException(e)
        }
    }


    suspend fun getAyahtInSura(): Resource<QuranApiResponse> {
        return try {
            handleSuccess(networkService.getAyahtInSura())
        } catch (e: Exception) {
            handleException(e)
        }
    }



*/



}