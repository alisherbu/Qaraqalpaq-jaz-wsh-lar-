package com.example.qaraqalpaqjazwshlar.poets

import com.example.qaraqalpaqjazwshlar.data.Poets
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase
import java.util.*
import kotlin.collections.ArrayList

class PoetPresenter(fragment: FragmentPoets, var view: PoetView) {
    private val dao = PoetsDatabase.getInstance(fragment.requireContext()).dao()
    private lateinit var poetList: List<Poets>
    fun getAllPoets() {
        poetList = dao.getAllPoetsAndId()
        view.setData(poetList)
    }

    fun filter(text: String) {
        val filteredNames = ArrayList<Poets>()

        poetList.filterTo(filteredNames) {
            it.poetName!!.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))
        }
        view.filteredNames(filteredNames)
    }
}