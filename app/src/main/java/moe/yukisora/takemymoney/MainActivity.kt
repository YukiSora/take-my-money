package moe.yukisora.takemymoney

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
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
    private lateinit var bottomNavigationView: BottomNavigationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        setSupportActionBar(toolbar)
        toolbar.title = "Special"

        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
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
                when (position) {
                    0 -> {
                        toolbar.title = "Special"
                        bottomNavigationView.selectedItemId = R.id.special_item
                    }
                    1 -> {
                        toolbar.title = "Games"
                        bottomNavigationView.selectedItemId = R.id.games_item
                    }
                    2 -> {
                        toolbar.title = "Setting"
                        bottomNavigationView.selectedItemId = R.id.setting_item
                    }
                }
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.special_item -> {
                    viewPager.setCurrentItem(0, true)
                    true
                }
                R.id.games_item -> {
                    viewPager.setCurrentItem(1, true)
                    true
                }
                R.id.setting_item -> {
                    viewPager.setCurrentItem(2, true)
                    true
                }
                else -> false
            }
        }
    }
}
