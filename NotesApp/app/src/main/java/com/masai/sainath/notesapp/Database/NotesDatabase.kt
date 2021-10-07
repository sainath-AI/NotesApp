package com.masai.sainath.notesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masai.sainath.notesapp.DAO.NotesDao
import com.masai.sainath.notesapp.Model.NotesEntity

@Database(entities = [NotesEntity::class], version = 1,exportSchema = false)
 abstract class NotesDatabase : RoomDatabase() {


    abstract fun notesDao(): NotesDao

    companion object{
        @Volatile
        var INSTANCE: NotesDatabase? = null

         fun getDatabaseInstances(context: Context): NotesDatabase? {
         val tempInstance= INSTANCE
             if(tempInstance!=null){
                 return tempInstance
             }
            synchronized(this){
                val roomDatabaseInstance= Room.databaseBuilder(context,
                    NotesDatabase::class.java,
                    "Notes").allowMainThreadQueries().build()
                INSTANCE=roomDatabaseInstance
                return  roomDatabaseInstance
            }
        }

    }





}