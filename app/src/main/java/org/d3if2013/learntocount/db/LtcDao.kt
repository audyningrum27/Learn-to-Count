package org.d3if2013.learntocount.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LtcDao {
    @Insert
    fun insert(ltc: LtcEntity)

    @Query("SELECT * FROM ltc ORDER BY id DESC")
    fun getLastLtc(): LiveData<List<LtcEntity>>

    @Query("DELETE FROM ltc")
    fun clearData()
}