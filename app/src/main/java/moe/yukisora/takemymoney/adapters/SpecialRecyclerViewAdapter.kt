package moe.yukisora.takemymoney.adapters

import android.content.Context
import android.graphics.Paint
import android.support.v4.content.res.ResourcesCompat
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
import java.text.DecimalFormat
import java.util.*
import java.util.concurrent.TimeUnit


class SpecialRecyclerViewAdapter(private val context: Context, private val specials: ArrayList<SpecialModel>) : RecyclerView.Adapter<SpecialRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_special, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(specials[position])
    }

    override fun getItemCount(): Int = specials.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val score: TextView = view.findViewById(R.id.score)
        private val logo: ImageView = view.findViewById(R.id.logo)
        private val name: TextView = view.findViewById(R.id.name)
        private val discount: TextView = view.findViewById(R.id.discount)
        private val originPrice: TextView = view.findViewById(R.id.originPrice)
        private val price: TextView = view.findViewById(R.id.price)
        private val endDate: TextView = view.findViewById(R.id.endDate)

        fun bindData(special: SpecialModel) {
            when ((DecimalFormat("#%").parse(special.score).toDouble() * 100).toInt()) {
                in 0 .. 50 -> score.setBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.badBackgroundColor, null))
                in 51 .. 70 -> score.setBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.normalBackgroundColor, null))
                in 71 .. 100 -> score.setBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.goodBackgroundColor, null))
            }
            score.text = special.score
            loadImage(special)
            name.text = special.name
            name.isSelected = true
            discount.text = DecimalFormat("#%").format(special.discount)
            originPrice.text = DecimalFormat("$#0.00").format(special.originPrice)
            originPrice.paintFlags = originPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            price.text = DecimalFormat("$#0.00").format(special.price)
            endDate.text = leftTime(special)
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

        private fun leftTime(special: SpecialModel): String {
            return if (special.endDate != null) {
                val currentDate = Date()
                val diff = special.endDate.time - currentDate.time
                val hours = TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS)
                if (hours >= 24) {
                    "${(hours / 24).toInt()} 天"
                } else {
                    "$hours 小时"
                }
            } else {
                "-"
            }
        }
    }
}
