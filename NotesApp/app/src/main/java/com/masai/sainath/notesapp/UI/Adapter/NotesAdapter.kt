package com.masai.sainath.notesapp.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.masai.sainath.notesapp.Model.NotesEntity
import com.masai.sainath.notesapp.R
import com.masai.sainath.notesapp.UI.Fragments.HomeFragmentDirections
import com.masai.sainath.notesapp.databinding.ItemNotesBinding

class NotesAdapter(val requireContext: Context, var notesList: List<NotesEntity>) :
    RecyclerView.Adapter<NotesAdapter.notesViewholder>() {

    fun filtering(newFilteredList: ArrayList<NotesEntity>) {
        notesList= newFilteredList
        notifyDataSetChanged()
    }

    class notesViewholder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewholder {
        return notesViewholder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewholder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitle.text = data.notesTitle
        holder.binding.notesSubtitle.text = data.notesSubTitle
        holder.binding.notesDate.text = data.notesDate

        when (data.notesPriority) {
            "1" -> {
                holder.binding.notespriority.setBackgroundResource(R.drawable.greenshape)
            }
            "2" -> {
                holder.binding.notespriority.setBackgroundResource(R.drawable.redshape)

            }
            "3" -> {
                holder.binding.notespriority.setBackgroundResource(R.drawable.yelloeshape)

            }

        }
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragment2ToEditNotesFragment2(data)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount() = notesList.size

}
