package moe.yukisora.takemymoney.networks

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moe.yukisora.takemymoney.models.SpecialModel
import org.jsoup.Jsoup


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

            val appid = it.attr("data-appid")
            val logoUrl = "https://steamdb.info/static/camo/apps/$appid/capsule_sm_120.jpg"
            val name = td[2].select("a").first().text()
            val category = td[2].select("span.category").first().text()
            val discount = td[3].text()
            val price = td[4].text()
            val score = td[5].select("span").first()?.text() ?: ""
            val endDate = td[6].attr("title")
            val startDate = td[7].attr("title")
            val releaseDate = td[8].attr("title")

            SpecialModel(appid, name, logoUrl, category, discount, price, score, endDate, startDate, releaseDate)
        }

        return specials
    }
}
