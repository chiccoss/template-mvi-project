package fr.sohayb.marvelapp.app.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val firstName: String,
    val lastName: String,
    val role : String,
    val email: String
)