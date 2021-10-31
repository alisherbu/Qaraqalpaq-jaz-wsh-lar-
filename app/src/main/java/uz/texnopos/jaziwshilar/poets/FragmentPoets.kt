package uz.texnopos.jaziwshilar.poets

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_poets_list.*
import uz.texnopos.jaziwshilar.R
import uz.texnopos.jaziwshilar.biography.BioActivity
import uz.texnopos.jaziwshilar.data.Poets
import uz.texnopos.jaziwshilar.data.PoetsDao
import uz.texnopos.jaziwshilar.data.PoetsDatabase
import uz.texnopos.jaziwshilar.main.MainActivity

class FragmentPoets : Fragment(R.layout.fragment_poets_list), PoetView {
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
        dao = PoetsDatabase.getInstance(requireContext()).dao()
        presenter = PoetPresenter(dao, this)
        presenter.getAllPoets()
        recyclerView.adapter = adapter

        val intent = Intent(requireContext(), BioActivity::class.java)
        adapter.setOnItemClickListener { id ->
            intent.putExtra(ID, id)
            startActivity(intent)
        }
    }

    override fun setData(models: List<Poets>) {
        adapter.models = models
    }

    override fun filteredNames(list: List<Poets>) {
        adapter.models = list
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search,menu)
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