package com.artistep.artistep

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.artistep.artistep.databinding.ActivityMainBinding
import com.artistep.artistep.page1.FilmFragment
import com.artistep.artistep.page2.ZoomFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private var container = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        container = binding.mainContainer.id

        val bottomBar = binding.bottomNav
        supportFragmentManager.beginTransaction().replace(container, FilmFragment()).commitAllowingStateLoss()

        bottomBar.setOnItemSelectedListener {
            getFragment(it)
        }
        bottomBar.setOnItemReselectedListener {
            Log.d("Main","menu reselected")
        }
    }

    private fun getFragment(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.bottom_bar_film -> {
                supportFragmentManager.beginTransaction().replace(container, FilmFragment()).commitAllowingStateLoss()
            }
            R.id.bottom_bar_zoomin -> {
                supportFragmentManager.beginTransaction().replace(container, ZoomFragment()).commitAllowingStateLoss()
            }
            else -> {
                supportFragmentManager.beginTransaction().replace(container, ZoomFragment()).commitAllowingStateLoss()
            }
        }
        return true
    }
}