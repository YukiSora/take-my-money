package moe.yukisora.takemymoney

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

class MainActivity : AppCompatActivity() {
    companion object {
        const val PAGER_COUNT = 3
    }
    private lateinit var viewPager: ViewPager;
    private lateinit var toolbar: Toolbar;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = "Special"

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return when(position) {
                    0 -> SpecialFragment.newInstance()
                    1 -> GamesFragment.newInstance()
                    else -> SettingFragment.newInstance()
                }
            }

            override fun getCount(): Int {
                return PAGER_COUNT
            }
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> toolbar.title = "Special"
                    1 -> toolbar.title = "Games"
                    2 -> toolbar.title = "Setting"
                }
            }
        })
    }
}
