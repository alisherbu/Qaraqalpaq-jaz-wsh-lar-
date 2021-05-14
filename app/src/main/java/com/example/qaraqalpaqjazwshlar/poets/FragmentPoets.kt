package com.example.qaraqalpaqjazwshlar.poets

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.biography.BioActivity
import com.example.qaraqalpaqjazwshlar.data.Poets
import com.example.qaraqalpaqjazwshlar.data.PoetsDao
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase
import kotlinx.android.synthetic.main.fragment_poets_list.*

class FragmentPoets : Fragment(R.layout.fragment_poets_list), PoetView {

    private lateinit var dao: PoetsDao
    private val adapter = PoetAdapter()
    private lateinit var presenter: PoetPresenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = PoetsDatabase.getInstance(requireContext()).dao()
        presenter = PoetPresenter(dao, this)
        presenter.getAllPoets()
        recyclerView.adapter = adapter

        etSearch.addTextChangedListener {
            presenter.filter(it.toString())
        }
        val intent = Intent(requireContext(), BioActivity::class.java)
        adapter.setOnItemClickListener { item, _, id ->
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }

    override fun setData(models: List<Poets>) {
        adapter.models = models
    }

    override fun filteredNames(list: List<Poets>) {
        adapter.models = list
    }
}