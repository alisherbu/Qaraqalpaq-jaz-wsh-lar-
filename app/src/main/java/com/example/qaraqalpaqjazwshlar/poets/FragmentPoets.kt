package com.example.qaraqalpaqjazwshlar.poets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqjazwshlar.R
import kotlinx.android.synthetic.main.fragment_poets_list.*

class FragmentPoets : Fragment(R.layout.fragment_poets_list), PoetView {
    val adapter = PoetAdapter()
    lateinit var presenter: PoetPresenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = PoetPresenter(this, this)
        presenter.getAllPoets()
        presenter.filterNames()
        presenter.startActivity()
        recyclerView.adapter = adapter
    }

    override fun setData(models: List<String>) {
        adapter.models = models
    }

    override fun filteredNames(list: List<String>) {
        adapter.models = list
    }
}