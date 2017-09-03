package moe.yukisora.takemymoney

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SpecialFragment : Fragment() {
    companion object {
        fun newInstance(): SpecialFragment {
            val args = Bundle()
            val fragment = SpecialFragment()
            fragment.arguments = args;

            return fragment
        }
    }

    private lateinit var adapter: SpecialRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var specials: ArrayList<SpecialData>

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
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = SpecialRecyclerViewAdapter(specials)
        recyclerView.adapter = adapter
        val divider = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_item))
        recyclerView.addItemDecoration(divider)
    }

    private fun initData() {
        for (i in 1..10) specials.add(SpecialData("Poi"))
        adapter.notifyDataSetChanged()
    }
}
