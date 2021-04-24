package com.example.qaraqalpaqjazwshlar.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.data.PoetsDao
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase
import com.example.qaraqalpaqjazwshlar.activity.BioActivity
import com.example.qaraqalpaqjazwshlar.ui.PoetAdapter
import kotlinx.android.synthetic.main.fragment_poets_list.*
import java.util.*
import kotlin.collections.ArrayList

class FragmentPoets : Fragment(R.layout.fragment_poets_list) {
    private val adapter = PoetAdapter()
    private lateinit var poetsList: List<String>
    private lateinit var dao: PoetsDao
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        dao = PoetsDatabase.getInstance(requireContext()).dao()
        val intent = Intent(requireContext(), BioActivity::class.java)
        adapter.setOnItemClickListener { _, _, poetName ->
            val id = dao.getIdByPoetName(poetName)
            intent.putExtra("id", id)

            startActivity(intent)
        }
        setData()
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(text: Editable) {
                filter(text.toString())
            }

        })
    }

    private fun setData() {
        poetsList = dao.getAllPoets()
        adapter.models = poetsList
    }

    private fun filter(text: String) {
        val filteredNames = ArrayList<String>()
        poetsList.filterTo(filteredNames) {
            it.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))
        }
        adapter.models = filteredNames
    }
}