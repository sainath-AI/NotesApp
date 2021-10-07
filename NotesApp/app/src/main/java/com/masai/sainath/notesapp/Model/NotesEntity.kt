package com.masai.sainath.notesapp.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
class NotesEntity(


    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

   // @ColumnInfo(name = "notes_titles")
    var notesTitle: String,

    //@ColumnInfo(name = "notes_subtitles")
    var notesSubTitle: String,

   // @ColumnInfo(name = "notes_date")
    var notesDate: String,

   // @ColumnInfo(name = "notes")
    var notes: String?,

   // @ColumnInfo(name = "notes_priority")
    var notesPriority: String?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(notesTitle)
        parcel.writeString(notesSubTitle)
        parcel.writeString(notesDate)
        parcel.writeString(notes)
        parcel.writeString(notesPriority)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NotesEntity> {
        override fun createFromParcel(parcel: Parcel): NotesEntity {
            return NotesEntity(parcel)
        }

        override fun newArray(size: Int): Array<NotesEntity?> {
            return arrayOfNulls(size)
        }
    }
}