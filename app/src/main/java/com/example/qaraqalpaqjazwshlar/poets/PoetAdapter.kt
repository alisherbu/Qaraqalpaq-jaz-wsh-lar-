package com.example.qaraqalpaqjazwshlar.poets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.data.Poets
import kotlinx.android.synthetic.main.item_poet.view.*

class PoetAdapter : RecyclerView.Adapter<PoetAdapter.PoetListViewHolder>() {
    inner class PoetListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun populateModel(model: Poets) {
            itemView.poetName.text = model.poetName
            itemView.setOnClickListener {
                onCLick.invoke(model.id)
            }
        }
    }

    var models = listOf<Poets>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onCLick: (id: Int) -> Unit = { _ ->

    }

    fun setOnItemClickListener(onCLick: (id: Int) -> Unit) {
        this.onCLick = onCLick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoetListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poet, parent, false)
        return PoetListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PoetListViewHolder, position: Int) {

        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_in_row)
        holder.itemView.startAnimation(animation)
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size

}