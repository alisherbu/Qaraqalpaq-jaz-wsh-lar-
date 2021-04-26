package com.example.qaraqalpaqjazwshlar.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.activity.BioActivity
import com.example.qaraqalpaqjazwshlar.data.PoetsDao
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase
import com.example.qaraqalpaqjazwshlar.ui.PoetAdapter
import kotlinx.android.synthetic.main.fragment_chosen.*

class FragmentChosen : Fragment(R.layout.fragment_chosen) {
    private lateinit var dao: PoetsDao
    private val adapter = PoetAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvChosen.adapter = adapter

        dao = PoetsDatabase.getInstance(requireContext()).dao()
        val intent = Intent(requireContext(), BioActivity::class.java)
        adapter.setOnItemClickListener { _, position, poetName ->
            val id = dao.getIdByPoetName(poetName)
            intent.putExtra("id", id)
            intent.putExtra("pos", position)
            startActivity(intent)
        }
    }

    override fun onResume() {
        setData()
        super.onResume()
    }

    private fun setData() {
        adapter.models = dao.getAllFavorites()
    }
}
