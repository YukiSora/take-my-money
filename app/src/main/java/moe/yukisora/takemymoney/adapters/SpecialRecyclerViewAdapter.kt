package moe.yukisora.takemymoney.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import moe.yukisora.takemymoney.R
import moe.yukisora.takemymoney.models.SpecialModel

class SpecialRecyclerViewAdapter(private val specials: ArrayList<SpecialModel>) : RecyclerView.Adapter<SpecialRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_special, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(specials[position])
    }

    override fun getItemCount(): Int = specials.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.name)
        private val discount: TextView = view.findViewById(R.id.discount)
        private val price: TextView = view.findViewById(R.id.price)

        internal fun bindData(specials: SpecialModel) {
            name.text = specials.name
            discount.text = specials.discount
            price.text = specials.price
        }
    }
}
