package com.example.soundBoardApp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface SBTuplesDatabaseDao {


    @Insert
    fun insert(tuple: SBTuple)

    /**
     * Selects and returns all rows in the table,
     * sorted by primary key in ascending order.
     */
    @Query("SELECT * FROM SoundBoard_Image_Sound_Tuples_Table ORDER BY tupleID ASC")
    fun getAllTuples(): List<SBTuple>


}