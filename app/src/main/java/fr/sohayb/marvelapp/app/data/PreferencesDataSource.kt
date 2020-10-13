package fr.sohayb.marvelapp.app.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

class PreferencesDataSource(private val context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

    private val tagCurrentTafseer = "CurrentTafseer"
    private val tagTutorialAlreadySeen = "TutorialAlreadySeen"


    //region Authorization


    //region Tutorial

    var tutorialAlreadySeen: Boolean
        get() {
            return preferences.getBoolean(tagTutorialAlreadySeen, false)
        }
        set(value) {
            preferences.edit()
                .putBoolean(tagTutorialAlreadySeen, value)
                .apply()
        }

    //endregion

    //region Add Car
/*
    var currentTafseer: String
        get() {
            return preferences.getString(tagCurrentTafseer,null).toString()
        }
        set(value) {
            preferences.edit()
                .putString(tagCurrentTafseer, value)
                .apply()
        }
*/
    //endregion

    //region Filter
/*
    var currentTafseer: CurrentTafseerPreferences?
        get() {
            return getObject<CurrentTafseerPreferences>(tagCurrentTafseer)
        }
        set(value) {
            putObject(value, tagCurrentTafseer)
        }

    fun saveTafseer(savedTafseer: CurrentTafseerPreferences): Boolean {
        return putObject(savedTafseer, tagCurrentTafseer)
    }*/

    //endregion

    private fun <T> putObject(`object`: T, key: String): Boolean {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        return preferences.edit().putString(key, jsonString).commit()
    }

    private inline fun <reified T> getObject(key: String): T? {
        //We read JSON String which was saved.
        val value = preferences.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

}