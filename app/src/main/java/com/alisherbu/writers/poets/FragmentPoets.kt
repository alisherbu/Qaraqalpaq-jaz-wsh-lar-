package com.alisherbu.writers.poets

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.alisherbu.writers.R
import com.alisherbu.writers.biography.BioActivity
import com.alisherbu.writers.data.PoetEntity
import com.alisherbu.writers.data.PoetsDao
import com.alisherbu.writers.data.PoetsDatabase
import com.alisherbu.writers.databinding.FragmentPoetsListBinding

class FragmentPoets : Fragment(R.layout.fragment_poets_list), PoetView {
    private lateinit var binding: FragmentPoetsListBinding

    companion object {
        const val ID = "id"
    }

    lateinit var menuItem: MenuItem
    private lateinit var dao: PoetsDao
    private val adapter = PoetAdapter()
    lateinit var presenter: PoetPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPoetsListBinding.bind(view)
        dao = PoetsDatabase.getInstance(requireContext()).dao()
        presenter = PoetPresenter(dao, this)
        presenter.getAllPoets()
        binding.recyclerView.addItemDecoration(CustomItemDecoration(requireContext()))
        binding.recyclerView.adapter = adapter

        val intent = Intent(requireContext(), BioActivity::class.java)
        adapter.setOnItemClickListener { id ->
            intent.putExtra(ID, id)
            startActivity(intent)
        }
    }

    override fun setData(models: List<PoetEntity>) {
        adapter.models = models
    }

    override fun filteredNames(list: List<PoetEntity>) {
        adapter.models = list
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        menuItem = menu.findItem(R.id.action_search)
        val viewSearch = menuItem.actionView as androidx.appcompat.widget.SearchView
        viewSearch.queryHint = getString(R.string.search)
        viewSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewSearch.clearFocus()
                presenter.filter(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.filter(newText!!)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}
