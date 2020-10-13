package fr.sohayb.marvelapp.app.database

import android.annotation.SuppressLint
import fr.sohayb.marvelapp.app.models.User


@SuppressLint("Registered")
class RoomDataSource {
    /*val database =
        AppDatabase.invoke(context = this@RoomDataSource)
*/

    /*
        suspend fun getAndfillRecyclerWithPhotos() = withContext(Dispatchers.IO) {
            val liveData = MutableLiveData<List<Picture>>()

            val pics = DataBaseFactory.instance?.photosDao()?.getAll()
                .let { it as ArrayList<Picture> }

            liveData.apply { postValue(pics) }
        }
    */
   /* fun InsertUser(user: User) {

        val liveData = MutableLiveData<User>()

        val pics = DataBaseFactory.instance?.usersDao().
            .let { it as ArrayList<Picture> }

        liveData.apply { postValue(pics) }

    }
*/
    fun updateUser(user: User) {
        DataBaseFactory.instance?.usersDao()?.updateUser(user)
    }

    fun insertUser(user: User) {
        DataBaseFactory.instance?.usersDao()?.insertUser(user)
    }

    fun deleteUser(user: User) {
        DataBaseFactory.instance?.usersDao()?.deleteUser(user)
    }



}