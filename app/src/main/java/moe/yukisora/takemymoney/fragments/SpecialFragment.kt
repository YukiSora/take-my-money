package moe.yukisora.takemymoney.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import moe.yukisora.takemymoney.R
import moe.yukisora.takemymoney.models.SpecialModel
import moe.yukisora.takemymoney.adapters.SpecialRecyclerViewAdapter

class SpecialFragment : Fragment() {
    companion object {
        fun newInstance(): SpecialFragment {
            val args = Bundle()
            val fragment = SpecialFragment()
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var adapter: SpecialRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var specials: ArrayList<SpecialModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_special, container, false)

        specials = ArrayList()

        initView(view)
        initData()

        return view
    }

    private fun initView(view: View) {
        // recycler view
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val divider = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_item))
        recyclerView.addItemDecoration(divider)
        adapter = SpecialRecyclerViewAdapter(specials)
        recyclerView.adapter = adapter

        // refresh layout
        refreshLayout = view.findViewById(R.id.refreshLayout)
        refreshLayout.setPrimaryColorsId(R.color.windowBackground, R.color.loadingColor)
        refreshLayout.refreshHeader = ClassicsHeader(activity)
        refreshLayout.refreshFooter = ClassicsFooter(activity)
        refreshLayout.setOnRefreshListener {
            refreshLayout.finishRefresh(2000)
        }
        refreshLayout.setOnLoadmoreListener {
            refreshLayout.finishLoadmore(2000)
        }
    }

    private fun initData() {
        for (i in 1..10) specials.add(SpecialModel("Poi"))
        adapter.notifyDataSetChanged()
    }
}
