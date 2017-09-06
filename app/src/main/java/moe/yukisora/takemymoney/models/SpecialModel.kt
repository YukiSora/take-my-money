package moe.yukisora.takemymoney.models

data class SpecialModel(
        val appid: String,
        val name: String,
        val logoUrl: String,
        val category: String,
        val discount: String,
        val price: String,
        val score: String,
        val endDate: String,
        val startDate: String,
        val releaseDate: String
)
