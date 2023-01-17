package uz.texnopos.jaziwshilar.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.texnopos.jaziwshilar.R
import uz.texnopos.jaziwshilar.biography.BioActivity
import uz.texnopos.jaziwshilar.data.PoetEntity
import uz.texnopos.jaziwshilar.data.PoetsDao
import uz.texnopos.jaziwshilar.data.PoetsDatabase
import uz.texnopos.jaziwshilar.databinding.FragmentChosenBinding
import uz.texnopos.jaziwshilar.poets.FragmentPoets.Companion.ID
import uz.texnopos.jaziwshilar.poets.PoetAdapter

class FragmentFavorite : Fragment(R.layout.fragment_chosen), FavoriteView {
    private lateinit var binding: FragmentChosenBinding
    private lateinit var presenter: FavoritePresenter
    private val adapter = PoetAdapter()
    private lateinit var dao: PoetsDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChosenBinding.bind(view)
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
