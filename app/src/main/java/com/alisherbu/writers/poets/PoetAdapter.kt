package com.alisherbu.writers.poets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alisherbu.writers.data.PoetEntity
import com.alisherbu.writers.databinding.ItemPoetBinding

class PoetAdapter : RecyclerView.Adapter<PoetAdapter.PoetListViewHolder>() {
    inner class PoetListViewHolder(private val binding: ItemPoetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: PoetEntity) {
            binding.poetName.text = model.poetName
            binding.root.setOnClickListener {
                onCLick.invoke(model.id)
            }
        }
    }

    var models = listOf<PoetEntity>()
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
        val binding = ItemPoetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PoetListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PoetListViewHolder, position: Int) {
        holder.bind(models[position])
    }

    override fun getItemCount() = models.size
}
