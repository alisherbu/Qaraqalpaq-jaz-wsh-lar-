package com.example.qaraqalpaqjazwshlar.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.biography.BioActivity
import com.example.qaraqalpaqjazwshlar.data.Poets
import com.example.qaraqalpaqjazwshlar.poets.PoetAdapter
import kotlinx.android.synthetic.main.fragment_chosen.*

class FragmentFavorite : Fragment(R.layout.fragment_chosen), FavoriteView {
    private lateinit var presenter: FavoritePresenter
    private val adapter = PoetAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvChosen.adapter = adapter
        rvChosen.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        presenter = FavoritePresenter(this, this)
        presenter.getAllFavorites()
        presenter.startActivity()
    }

    override fun onResume() {
        presenter.getAllFavorites()
        super.onResume()
    }

    override fun setData(models: List<Poets>) {
        adapter.models = models
        if (adapter.models.isNotEmpty()) linearLayout.visibility=View.VISIBLE
        else linearLayout.visibility=View.INVISIBLE
    }

    override fun startActivity() {
        val intent = Intent(requireContext(), BioActivity::class.java)
        adapter.setOnItemClickListener { item, _, id ->
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }
}
