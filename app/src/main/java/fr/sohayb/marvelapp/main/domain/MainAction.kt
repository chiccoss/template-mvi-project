package fr.sohayb.marvelapp.main.domain

import fr.sohayb.marvelapp.base.domain.ActionType

sealed class MainAction: ActionType {

    object InitApplication : MainAction()
    //data class GetAyahTafseer(var tafseerId : Int,var suraNumber: Int,var ayahId: Int): MainAction()
    object GetAllCharacters: MainAction()


}