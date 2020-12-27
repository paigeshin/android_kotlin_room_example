package com.paigesoftware.android_room_tutorial.data

import androidx.lifecycle.LiveData
import androidx.room.*

//Data Access Object
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //중복유저시 아무 것도 발생시키지 않는다.
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

}