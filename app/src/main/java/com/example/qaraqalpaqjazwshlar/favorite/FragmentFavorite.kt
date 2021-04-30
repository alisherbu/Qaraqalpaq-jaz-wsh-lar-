package com.example.qaraqalpaqjazwshlar.favorite

import FavoritePresenter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.poets.PoetAdapter
import kotlinx.android.synthetic.main.fragment_chosen.*

class FragmentFavorite : Fragment(R.layout.fragment_chosen), FavoriteView {
    lateinit var presenter: FavoritePresenter
    val adapter = PoetAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvChosen.adapter = adapter
        presenter = FavoritePresenter(this, this)
        presenter.getAllFavorites()
        presenter.startActivity()
    }
    override fun onResume() {
        presenter.getAllFavorites()
        super.onResume()
    }
    override fun setData(models: List<String>) {
        adapter.models = models
    }
}
