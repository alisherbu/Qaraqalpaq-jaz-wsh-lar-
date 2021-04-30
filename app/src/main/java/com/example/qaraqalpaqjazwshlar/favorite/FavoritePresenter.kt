package com.example.qaraqalpaqjazwshlar.favorite

import android.content.Intent
import com.example.qaraqalpaqjazwshlar.biography.BioActivity
import com.example.qaraqalpaqjazwshlar.data.PoetsDao
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase

class FavoritePresenter(private var fragment: FragmentFavorite, var view: FavoriteView) {
    private var dao: PoetsDao = PoetsDatabase.getInstance(fragment.requireContext()).dao()

    fun getAllFavorites() {
        view.setData(dao.getAllFavorites())
    }


    fun startActivity() {
        val intent = Intent(fragment.requireContext(), BioActivity::class.java)
        fragment.adapter.setOnItemClickListener { _, _, poetName ->
            val id = dao.getIdByPoetName(poetName)
            intent.putExtra("id", id)
            fragment.startActivity(intent)
        }
    }


}