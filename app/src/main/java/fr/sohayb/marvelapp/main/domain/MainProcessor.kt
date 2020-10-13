package fr.sohayb.marvelapp.main.domain

import android.content.Context
import fr.sohayb.marvelapp.app.api.Resource
import fr.sohayb.marvelapp.base.domain.ActionType
import fr.sohayb.marvelapp.base.domain.ProcessorResultCallback
import fr.sohayb.marvelapp.base.domain.ProcessorType
import fr.sohayb.marvelapp.main.data.MainRepository
import kotlinx.coroutines.delay


private const val SPLASH_DURATION = 1500L
class MainProcessor(
    val mainRepository: MainRepository,
    private val context: Context
) : ProcessorType {
    override suspend fun process(action: ActionType, next: ProcessorResultCallback) {
        (action as? MainAction)?.let {
            when (it) {
                //is MainAction.GetAyahTafseer -> getAyahTafseer(
               //     it.tafseerId,
               //     it.suraNumber,
               //     it.ayahId,
               //     next
               // )
                is MainAction.InitApplication -> {
                    //initApplication(next)
                }
                is MainAction.GetAllCharacters ->{
                    //getAllCharacters(next)
                }

            }
        }
    }

  /*  private fun getAllCharacters(next: ProcessorResultCallback) {
        mainRepository.getAllCharacters().apply {
        when (this) {
             is Resource.Success -> next(MainResult.GotAyatInSura(data))
             is Resource.Error -> next(MainResult.MainError(error))
         }
        //}
    }


    private suspend fun initApplication(next: ProcessorResultCallback) {
        delay(SPLASH_DURATION)
        next(MainResult.InitApplication)
    }
*/


    suspend fun getAyahtInSura(
        next: ProcessorResultCallback
    ) {
        //mainRepository.getAyahtInSura().apply {
           /* when (this) {
                is Resource.Success -> next(MainResult.GotAyatInSura(data))
                is Resource.Error -> next(MainResult.MainError(error))
            }*/
        //}
    }



}