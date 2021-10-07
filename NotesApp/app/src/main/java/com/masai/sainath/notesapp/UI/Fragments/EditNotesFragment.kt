package com.masai.sainath.notesapp.UI.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.masai.sainath.notesapp.Model.NotesEntity
import com.masai.sainath.notesapp.R
import com.masai.sainath.notesapp.databinding.FragmentEditNotesBinding
import com.masai.sainath.notesapp.viewmodel.NotesViewmodel
import java.util.*


class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priority: String = "1"
    val viewModel: NotesViewmodel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)


        binding.etTitle.setText(oldNotes.data.notesTitle)
        binding.etsubTitle.setText(oldNotes.data.notesSubTitle)
        binding.etTakenotes.setText(oldNotes.data.notes)

        when (oldNotes.data.notesPriority) {
            "1" -> {
                priority = "1"

                binding.greenDot.setImageResource(R.drawable.ic_baseline_done_24)
                binding.yellowDot.setImageResource(0)
                binding.RedDot.setImageResource(0)
            }
            "2" -> {
                priority = "2"

                binding.RedDot.setImageResource(R.drawable.ic_baseline_done_24)

                binding.yellowDot.setImageResource(0)
                binding.greenDot.setImageResource(0)

            }
            "3" -> {
                priority = "3"
                binding.yellowDot.setImageResource(R.drawable.ic_baseline_done_24)
                binding.greenDot.setImageResource(0)
                binding.RedDot.setImageResource(0)

            }

        }

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
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title= binding.etTitle.text.toString()
        val subtitle= binding.etsubTitle.text.toString()
        val notes= binding.etTakenotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data= NotesEntity(
            oldNotes.data.id,
            notesTitle = title,
            notesSubTitle = subtitle,
            notes =notes,
            notesDate =notesDate.toString(),
            notesPriority = priority
        )
        viewModel.updateNotes(data)
        Toast.makeText(context, "notes updated successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment2_to_homeFragment2)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.deletemenu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      if(item.itemId==R.id.id_delete){

          val bottomsheet: BottomSheetDialog= BottomSheetDialog(requireContext())
          bottomsheet.setContentView(R.layout.bottomsheet)

          val textviewYes= bottomsheet.findViewById<TextView>(R.id.yes)
          val textviewNo= bottomsheet.findViewById<TextView>(R.id.no)

          textviewYes?.setOnClickListener {
              viewModel.deleteNotes(oldNotes.data.id!!)
              bottomsheet.dismiss()


          }
          textviewNo?.setOnClickListener {
              bottomsheet.dismiss()
          }

          bottomsheet.show()

      }
        return super.onOptionsItemSelected(item)
    }


}