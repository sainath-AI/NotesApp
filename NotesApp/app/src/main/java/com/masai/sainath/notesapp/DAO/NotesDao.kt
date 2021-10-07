package com.masai.sainath.notesapp.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.masai.sainath.notesapp.Model.NotesEntity


@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM Notes WHERE notesPriority=2")
    fun getHighNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM Notes WHERE notesPriority=3")
    fun getMediumNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM Notes WHERE notesPriority=1")
    fun getLowNotes(): LiveData<List<NotesEntity>>

//    @Query("SELECT * FROM Notes ORDER BY notes_priority DESC")
//    fun HighToLow(): LiveData<List<NotesEntity>>
//
//    @Query("SELECT * FROM Notes ORDER BY notes_priority ASC ")
//    fun LowToHigh(): LiveData<List<NotesEntity>>




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: NotesEntity)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id: Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNotes(notes: NotesEntity)

}