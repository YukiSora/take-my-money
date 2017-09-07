package moe.yukisora.takemymoney.networks

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moe.yukisora.takemymoney.models.SpecialModel
import org.jsoup.Jsoup
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


object GetSpecials {
    fun getSpecials(observer: Observer<ArrayList<SpecialModel>>) {
        RetrofitServiceGenerator.steamdbGenerator().getSpecials()
                .map { html -> parse(html) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    private fun parse(html: String): ArrayList<SpecialModel> {
        val specials = ArrayList<SpecialModel>()
        val document = Jsoup.parse(html)
        val tbody = document.select("table.table-sales tbody").first()
        tbody.select("tr").mapTo(specials) {
            val td = it.select("td")
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)

            val appid = it.attr("data-appid")
            val logoUrl = "https://steamdb.info/static/camo/apps/$appid/capsule_sm_120.jpg"
            val name = td[2].select("a").first().text()
            val category = td[2].select("span.category").first()?.text() ?: ""
            val discount = DecimalFormat("#%").parse(td[3].text()).toDouble()
            val price = DecimalFormat("#.00").parse(td[4].text().substring(1)).toDouble()
            val originPrice = DecimalFormat("#.00").format(price / (1 + discount)).toDouble()
            val score = td[5].select("span").first()?.text() ?: ""
            val endDateString = td[6].attr("title")
            val endDate = if (endDateString.isNotEmpty()) dateFormat.parse(endDateString) else null
            val startDateString = td[7].attr("title")
            val startDate = if (startDateString.isNotEmpty()) dateFormat.parse(startDateString) else null
            val releaseDateString = td[8].attr("title")
            val releaseDate = if (releaseDateString.isNotEmpty()) dateFormat.parse(releaseDateString) else null

            SpecialModel(appid, name, logoUrl, category, discount, price, originPrice, score, endDate, startDate, releaseDate)
        }

        return specials
    }
}
