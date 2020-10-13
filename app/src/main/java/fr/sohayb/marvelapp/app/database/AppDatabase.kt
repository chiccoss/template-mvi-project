package fr.sohayb.marvelapp.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.sohayb.marvelapp.app.database.daos.UserDao
import fr.sohayb.marvelapp.app.models.*



@Database(
    entities = [(User::class)],
    version = 1,
    exportSchema = true
)


abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UserDao


}