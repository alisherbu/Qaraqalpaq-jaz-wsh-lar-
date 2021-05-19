package uz.texnopos.jaziwshilar.poets

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.texnopos.jaziwshilar.biography.BioActivity
import uz.texnopos.jaziwshilar.data.Poets
import uz.texnopos.jaziwshilar.data.PoetsDao
import uz.texnopos.jaziwshilar.data.PoetsDatabase
import kotlinx.android.synthetic.main.fragment_poets_list.*
import uz.texnopos.jaziwshilar.R

class FragmentPoets : Fragment(R.layout.fragment_poets_list), PoetView {
    companion object {
        const val ID = "id"
    }

    private lateinit var dao: PoetsDao
    private val adapter = PoetAdapter()
    lateinit var presenter: PoetPresenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = PoetsDatabase.getInstance(requireContext()).dao()
        presenter = PoetPresenter(dao, this)
        presenter.getAllPoets()
        recyclerView.adapter = adapter

        val intent = Intent(requireContext(), BioActivity::class.java)
        adapter.setOnItemClickListener { id ->
            intent.putExtra(ID, id)
            startActivity(intent)
        }
    }

    override fun setData(models: List<Poets>) {
        adapter.models = models
    }

    override fun filteredNames(list: List<Poets>) {
        adapter.models = list
    }
}