package moe.yukisora.takemymoney

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        const val PAGER_COUNT = 3
    }
    private lateinit var viewPager:ViewPager;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment? {
                return when(position) {
                    0 -> SpecialFragment.newInstance()
                    1 -> GamesFragment.newInstance()
                    2 -> SettingFragment.newInstance()
                    else -> null
                }
            }

            override fun getCount(): Int {
                return PAGER_COUNT
            }
        }
    }
}
