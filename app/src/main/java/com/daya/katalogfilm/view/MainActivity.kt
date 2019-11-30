package com.daya.katalogfilm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.daya.katalogfilm.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNav()

    }


    private fun setupBottomNav() {
        /*val navhost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        */
        val navController = findNavController(R.id.nav_host_fragment)


        bottomnav.setupWithNavController(navController)

        navHostListener(navController)

    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment).navigateUp()

    private fun navHostListener(navHostController: NavController) {
        navHostController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieFragment -> {
                    bottomnav.visibility = View.VISIBLE
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.title = getString(R.string.app_name)
                }
                R.id.tvsShowFragment -> {
                    bottomnav.visibility = View.VISIBLE
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.title = getString(R.string.app_name)

                }
                R.id.detailFragment -> {
                    bottomnav.visibility = View.GONE
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.title = getString(R.string.detail)
                }
                R.id.detailTvShowFragment ->{
                    bottomnav.visibility = View.GONE
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.title = getString(R.string.detail)
                }
                R.id.favoriteFragment->{
                    bottomnav.visibility = View.VISIBLE
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.title = getString(R.string.app_name)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                findNavController(R.id.nav_host_fragment).navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
