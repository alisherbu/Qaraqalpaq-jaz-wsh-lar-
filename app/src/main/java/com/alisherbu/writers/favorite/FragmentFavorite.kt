package com.alisherbu.writers.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.alisherbu.writers.R
import com.alisherbu.writers.biography.BioActivity
import com.alisherbu.writers.data.PoetEntity
import com.alisherbu.writers.data.PoetsDao
import com.alisherbu.writers.data.PoetsDatabase
import com.alisherbu.writers.databinding.FragmentChosenBinding
import com.alisherbu.writers.poets.FragmentPoets.Companion.ID
import com.alisherbu.writers.poets.PoetAdapter
import com.alisherbu.writers.utils.CustomItemDecoration

class FragmentFavorite : Fragment(R.layout.fragment_chosen), FavoriteView {
    private lateinit var binding: FragmentChosenBinding
    private lateinit var presenter: FavoritePresenter
    private val adapter = PoetAdapter()
    private lateinit var dao: PoetsDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChosenBinding.bind(view)
        binding.rvChosen.addItemDecoration(CustomItemDecoration(requireContext()))
        binding.rvChosen.adapter = adapter
        dao = PoetsDatabase.getInstance(requireContext()).dao()
        presenter = FavoritePresenter(dao, this)
        presenter.getAllFavorites()
        val intent = Intent(requireContext(), BioActivity::class.java)
        adapter.setOnItemClickListener { id ->
            intent.putExtra(ID, id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        presenter.getAllFavorites()
        super.onResume()
    }

    override fun setData(models: List<PoetEntity>) {
        adapter.models = models
        if (adapter.models.isEmpty()) binding.linearLayout.visibility = View.VISIBLE
        else binding.linearLayout.visibility = View.INVISIBLE
    }
}
