package com.example.qaraqalpaqjazwshlar.poets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.data.Poets
import kotlinx.android.synthetic.main.item_poet.view.*

class PoetAdapter(): RecyclerView.Adapter<PoetAdapter.PoetListViewHolder>() {
    inner class PoetListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun populateModel(model: Poets, pos: Int) {
            itemView.poetName.text = model.poetName
            itemView.setOnClickListener {
                onCLick.invoke(it, pos, model.id)
            }
        }
    }

    var models = listOf<Poets>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onCLick: (itemView: View, pos: Int, poetName: Int) -> Unit = { _, _, _ ->

    }

    fun setOnItemClickListener(onCLick: (itemView: View, pos: Int, id: Int) -> Unit) {
        this.onCLick = onCLick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoetListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poet, parent, false)
        return PoetListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PoetListViewHolder, position: Int) {
        holder.populateModel(models[position], position)
    }

    override fun getItemCount() = models.size

}