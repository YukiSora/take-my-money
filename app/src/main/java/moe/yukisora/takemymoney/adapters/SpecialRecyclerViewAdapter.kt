package moe.yukisora.takemymoney.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import moe.yukisora.takemymoney.R
import moe.yukisora.takemymoney.models.SpecialModel

class SpecialRecyclerViewAdapter(private val context: Context, private val specials: ArrayList<SpecialModel>) : RecyclerView.Adapter<SpecialRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_special, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(specials[position])
    }

    override fun getItemCount(): Int = specials.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val logo: ImageView = view.findViewById(R.id.logo)
        private val name: TextView = view.findViewById(R.id.name)
        private val discount: TextView = view.findViewById(R.id.discount)
        private val price: TextView = view.findViewById(R.id.price)

        fun bindData(special: SpecialModel) {
            loadImage(special)
            name.text = special.name
            discount.text = special.discount
            price.text = special.price
        }

        private fun loadImage(special: SpecialModel) {
            Picasso.with(context)
                    .load(special.logoUrl)
                    .placeholder(R.drawable.animation_progress)
                    .error(R.drawable.bitmap_reload)
                    .into(logo, object : Callback {
                        override fun onSuccess() {
                            logo.isClickable = false
                        }

                        override fun onError() {
                            logo.setOnClickListener { loadImage(special) }
                        }
                    })
        }
    }
}
