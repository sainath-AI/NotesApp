package com.masai.sainath.notesapp.UI.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.masai.sainath.notesapp.Model.NotesEntity
import com.masai.sainath.notesapp.R
import com.masai.sainath.notesapp.databinding.FragmentCreateNoteFragmentsBinding
import com.masai.sainath.notesapp.viewmodel.NotesViewmodel
import java.util.*


class CreateNoteFragments : Fragment() {


    lateinit var binding: FragmentCreateNoteFragmentsBinding

     var priority: String="1"
    val viewModel: NotesViewmodel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNoteFragmentsBinding.inflate(layoutInflater,container,false)

        binding.greenDot.setImageResource(R.drawable.ic_baseline_done_24)


        binding.greenDot.setOnClickListener {
            priority="1"
            binding.greenDot.setImageResource(R.drawable.ic_baseline_done_24)
            binding.yellowDot.setImageResource(0)
            binding.RedDot.setImageResource(0)
        }
         binding.RedDot.setOnClickListener {
             priority="2"
             binding.RedDot.setImageResource(R.drawable.ic_baseline_done_24)

             binding.yellowDot.setImageResource(0)
             binding.greenDot.setImageResource(0)
        }
        binding.yellowDot.setOnClickListener {
            priority="3"
            binding.yellowDot.setImageResource(R.drawable.ic_baseline_done_24)

            binding.greenDot.setImageResource(0)
            binding.RedDot.setImageResource(0)
        }

        binding.DoneNotes.setOnClickListener {
            createNotes(it)
        }


        return binding.root
    }

    private fun createNotes(it: View?) {

        val title= binding.etTitle.text.toString()
        val subtitle= binding.etsubTitle.text.toString()
        val notes= binding.etTakenotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data=NotesEntity(null,
            notesTitle = title,
            notesSubTitle = subtitle,
            notes =notes,
            notesDate =notesDate.toString(),
            notesPriority = priority
        )
        viewModel.addNotes(data)
        Toast.makeText(context, "notes created successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragments3_to_homeFragment2)
    }


}