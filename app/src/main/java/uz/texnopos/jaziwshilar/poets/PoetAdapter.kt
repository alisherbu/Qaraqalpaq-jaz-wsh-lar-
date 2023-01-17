package uz.texnopos.jaziwshilar.poets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_poet.view.*
import uz.texnopos.jaziwshilar.R
import uz.texnopos.jaziwshilar.data.Poets

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
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size
}
