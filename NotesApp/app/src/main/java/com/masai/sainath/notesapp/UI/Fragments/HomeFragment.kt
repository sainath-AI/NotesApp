package com.masai.sainath.notesapp.UI.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masai.sainath.notesapp.Model.NotesEntity
import com.masai.sainath.notesapp.R
import com.masai.sainath.notesapp.UI.Adapter.NotesAdapter
import com.masai.sainath.notesapp.databinding.FragmentHomeBinding
import com.masai.sainath.notesapp.viewmodel.NotesViewmodel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewmodel by viewModels()

    var oldMyNotes= arrayListOf<NotesEntity>()
    lateinit var adapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerview.layoutManager = staggeredGridLayoutManager

        // GET ALll notes
        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->

            oldMyNotes=notesList as ArrayList<NotesEntity>
          adapter=NotesAdapter(requireContext(), notesList)
            binding.recyclerview.adapter = adapter



        })

        // get filtered notes
        binding.filter.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes=notesList as ArrayList<NotesEntity>
                adapter=NotesAdapter(requireContext(), notesList)
                binding.recyclerview.adapter = adapter
            })

        }

        binding.High.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes=notesList as ArrayList<NotesEntity>
                adapter=NotesAdapter(requireContext(), notesList)
                binding.recyclerview.adapter = adapter

            })

        }
        binding.medium.setOnClickListener {

            viewModel.getMediumNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes=notesList as ArrayList<NotesEntity>
                adapter=NotesAdapter(requireContext(), notesList)
                binding.recyclerview.adapter = adapter

            })
        }
        binding.low.setOnClickListener {

            viewModel.getLowNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes=notesList as ArrayList<NotesEntity>
                adapter=NotesAdapter(requireContext(), notesList)
                binding.recyclerview.adapter = adapter

            })
        }


        binding.newNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment2_to_createNoteFragments3)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_notes, menu)
        val item = menu.findItem(R.id.app_bar_search)

        val searchView = item.actionView as SearchView

        searchView.queryHint = "Search here ..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                NotesFiltering(newText)

                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(newText: String?) {
        val newFilteredList= arrayListOf<NotesEntity>()
        for (i in oldMyNotes) {
            if (i.notesTitle.contains(newText!!) || i.notesSubTitle.contains(newText!!)) {

                newFilteredList.add(i)
            }

        }
        adapter.filtering(newFilteredList)
    }



}