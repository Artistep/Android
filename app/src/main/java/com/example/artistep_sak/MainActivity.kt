package com.example.artistep_sak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.artistep_sak.MainFragment.FilmFragment
import com.example.artistep_sak.SecondFragment.ZoomInFragment
import com.example.artistep_sak.databinding.ActivityMainBinding
import com.example.exoplayer.utils.ExoPlayerRecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_film.*

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private var container = -1

    var mRecyclerView: ExoPlayerRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        container = binding.mainContainer.id



        val bottomBar = binding.bottomNav
        supportFragmentManager.beginTransaction().replace(container, FilmFragment())
            .commitAllowingStateLoss()

        bottomBar.setOnItemSelectedListener {
            getFragment(it)
        }
        bottomBar.setOnItemReselectedListener {
            Log.d("Main", "menu reselected")
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.title_bar, menu)
        return true
    }
    private fun getFragment(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.bottom_nav_film -> {
                supportFragmentManager.beginTransaction().replace(container, FilmFragment())
                    .commitAllowingStateLoss()
                setSupportActionBar(toolbar)
                supportActionBar?.setDisplayShowTitleEnabled(false)
            }
            else -> {
                supportFragmentManager.beginTransaction().replace(container, ZoomInFragment())
                    .commitAllowingStateLoss()
            }
        }
        return true
    }
}