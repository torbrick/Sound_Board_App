package com.example.soundBoardApp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * data base access object for the SBTuple class
 */

@Dao
interface SBTuplesDatabaseDao {

    /**
    * conflict replace strategy, so that worker doesn't duplicate entries in test
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseTuple: SBDatabaseTuple)

    /**
     * Selects and returns all rows in the table,
     * sorted by primary key in ascending order.
     */
    @Query("SELECT * FROM SoundBoard_Image_Sound_Tuples_Table ORDER BY tupleID ASC")
    fun getAllTuples(): LiveData<List<SBDatabaseTuple>>

    @Query("DELETE FROM SoundBoard_Image_Sound_Tuples_Table")
    fun deleteAll()
}