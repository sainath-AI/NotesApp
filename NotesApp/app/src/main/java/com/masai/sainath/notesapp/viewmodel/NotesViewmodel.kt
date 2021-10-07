package com.masai.sainath.notesapp.viewmodel

import android.app.Application
import android.util.AndroidException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.masai.sainath.notesapp.Database.NotesDatabase
import com.masai.sainath.notesapp.Model.NotesEntity
import com.masai.sainath.notesapp.repo.NotesRepo

class NotesViewmodel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepo

    init {
        val dao= NotesDatabase.getDatabaseInstances(application)!!.notesDao()
        repository= NotesRepo(dao)
    }

    fun addNotes(notes:NotesEntity){
        repository.inserNotes(notes)
    }
    fun getNotes(): LiveData<List<NotesEntity>> = repository.getAllNotes()
    fun getHighNotes(): LiveData<List<NotesEntity>> = repository.getHighNotes()
    fun getMediumNotes(): LiveData<List<NotesEntity>> = repository.getMediumNotes()
    fun getLowNotes(): LiveData<List<NotesEntity>> = repository.getLowNotes()




    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }
    fun updateNotes(notes: NotesEntity){
        repository.updateNotes(notes)

    }
}