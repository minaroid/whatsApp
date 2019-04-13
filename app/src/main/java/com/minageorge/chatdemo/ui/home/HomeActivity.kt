package com.minageorge.chatdemo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.minageorge.chatdemo.R
import com.minageorge.chatdemo.adapters.HomePagerAdapter
import com.minageorge.chatdemo.store.models.rooms.RoomsEntity
import com.minageorge.chatdemo.store.models.subscribers.SubscribersEntity
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import java.util.*


class HomeActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel

    private var currentState = false
    private lateinit var upAnimation: TranslateAnimation
    private lateinit var downAnimation: TranslateAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "CHAT DEMO"

        upAnimation = TranslateAnimation(0f, 0f, 0f, -280f)
        upAnimation.duration = 250
        upAnimation.fillAfter = true
        downAnimation = TranslateAnimation(0f, 0f, -280f, 0f)
        downAnimation.duration = 250
        downAnimation.fillAfter = true

        mainViewPager.adapter = HomePagerAdapter(supportFragmentManager)
        mainViewPager.setCurrentItem(1, true)
        mainViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (position == 0) {
                    cameraState()
                } else if (currentState) {
                    normalState()
                }
            }
        })
        tab_layout.setupWithViewPager(mainViewPager)
        setupTabLayout()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

    }

    private fun setupTabLayout() {
        val layout = (tab_layout.getChildAt(0) as LinearLayout).getChildAt(0) as LinearLayout
        val layoutParams = layout.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 0.4f
        layout.layoutParams = layoutParams
        tab_layout.getTabAt(0)!!.icon = resources.getDrawable(R.drawable.ic_camera)
        tab_layout.getTabAt(1)!!.customView = LayoutInflater.from(this).inflate(R.layout.tab_chat, null)
        tab_layout.getTabAt(2)!!.customView = LayoutInflater.from(this).inflate(R.layout.tab_status, null)
        tab_layout.getTabAt(3)!!.customView = LayoutInflater.from(this).inflate(R.layout.tab_calls, null)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            findViewById(R.id.callCounter).setBackground(Main.getDrawable(MainActivity.this, R.drawable.bg_circle_tab_counter_unselected));
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            findViewById(R.id.statusDot).setBackground(Main.getDrawable(MainActivity.this, R.drawable.ic_dot_unselected));
//        }
    }

    private fun getTabView(pos: Int): View? {
        return when (pos) {
            0 -> LayoutInflater.from(this).inflate(R.layout.abc_action_bar_title_item, null)
            1 -> LayoutInflater.from(this).inflate(R.layout.abc_action_bar_title_item, null)
            2 -> LayoutInflater.from(this).inflate(R.layout.abc_action_bar_title_item, null)
            else -> return null
        }
    }


    private fun cameraState() {
        currentState = true
        app_bar.animation = upAnimation
        upAnimation.start()
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        fab.visibility = View.GONE
    }

    private fun normalState() {
        currentState = false
        app_bar.animation = downAnimation
        downAnimation.start()
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        fab.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_search -> insertValue()
        }
        return true;
    }

    fun insertValue() {

//        viewModel.insertSubscriberObservable.onNext(SubscribersEntity( System.currentTimeMillis(),1553771768031,"fwfqwf","wfwqf"))
//        viewModel.insertRoomObservable.onNext(
//            RoomsEntity(
//                System.currentTimeMillis(),
//                "Room-3",
//                "ffewfewfqfqw",
//                "gfefewfewfbfb",
//                Date(),
//                5
//            )
//        )
    }
}
