package moe.yukisora.takemymoney.models

import java.util.*

data class SpecialModel(
        val appid: String,
        val name: String,
        val logoUrl: String,
        val category: String,
        val discount: Double,
        val higherDiscount: Boolean,
        val price: Double,
        val originPrice: Double,
        val score: String,
        val endDate: Date?,
        val startDate: Date?,
        val releaseDate: Date?
)
