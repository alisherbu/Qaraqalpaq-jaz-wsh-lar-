package com.example.qaraqalpaqjazwshlar.poets

import android.content.Intent
import androidx.core.widget.addTextChangedListener
import com.example.qaraqalpaqjazwshlar.biography.BioActivity
import com.example.qaraqalpaqjazwshlar.data.PoetsDao
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase
import kotlinx.android.synthetic.main.fragment_poets_list.*
import java.util.*
import kotlin.collections.ArrayList

class PoetPresenter(private var fragment: FragmentPoets, var view: PoetView) {
    private var dao: PoetsDao = PoetsDatabase.getInstance(fragment.requireContext()).dao()
    private var poetList = dao.getAllPoets()

    fun getAllPoets() {
        view.setData(poetList)
    }


    fun startActivity() {
        val intent = Intent(fragment.requireContext(), BioActivity::class.java)
        fragment.adapter.setOnItemClickListener { _, _, poetName ->
            val id = dao.getIdByPoetName(poetName)
            intent.putExtra("id", id)
            fragment.startActivity(intent)
        }
    }

    fun filterNames() {
        fragment.etSearch.addTextChangedListener {
            filter(it.toString())
        }
    }

    private fun filter(text: String) {
        val filteredNames = ArrayList<String>()
        poetList.filterTo(filteredNames) {
            it.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))
        }
        view.filteredNames(filteredNames)

    }
}