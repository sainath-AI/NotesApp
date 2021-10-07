package com.masai.sainath.notesapp.repo

import androidx.lifecycle.LiveData
import com.masai.sainath.notesapp.DAO.NotesDao
import com.masai.sainath.notesapp.Model.NotesEntity

class NotesRepo (val dao: NotesDao){

    fun getAllNotes(): LiveData<List<NotesEntity>>{
        return dao.getNotes()
    }

    fun getLowNotes(): LiveData<List<NotesEntity>> = dao.getLowNotes()
    fun getMediumNotes(): LiveData<List<NotesEntity>> = dao.getMediumNotes()
    fun getHighNotes(): LiveData<List<NotesEntity>> = dao.getHighNotes()


    fun inserNotes(notes: NotesEntity){
        dao.insertNotes(notes)
    }
    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }
    fun updateNotes(notes:NotesEntity){
        dao.updateNotes(notes)
    }

}