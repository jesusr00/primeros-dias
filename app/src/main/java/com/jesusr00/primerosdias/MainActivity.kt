package com.jesusr00.primerosdias

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.jesusr00.primerosdias.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_direction_council, R.id.nav_cloister,
                R.id.nav_guide_teachers, R.id.nav_map, R.id.nav_scientific_groups,
                R.id.nav_uci_info, R.id.nav_useful_data, R.id.nav_feu_secretariat
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val navBuilder = NavOptions.Builder()
            .setEnterAnim(R.anim.fade_in).setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in).setPopExitAnim(R.anim.fade_out)
        val navOptions = navBuilder.setPopUpTo(R.id.nav_uci_info, false).build()
        val navHomeOptions = navBuilder.setLaunchSingleTop(true).build()


        navView.setNavigationItemSelectedListener { menuItem ->

            drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
                override fun onDrawerStateChanged(newState: Int) {}

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

                override fun onDrawerOpened(drawerView: View) {}

                override fun onDrawerClosed(drawerView: View) {

                    when (menuItem.itemId) {
                        R.id.nav_direction_council -> {
                            navController.navigate(R.id.nav_direction_council, null, navOptions)
                        }
                        R.id.nav_guide_teachers -> {
                            navController.navigate(R.id.nav_guide_teachers, null, navOptions)
                        }
                        R.id.nav_map -> {
                            navController.navigate(R.id.nav_map, null, navOptions)
                        }
                        R.id.nav_scientific_groups -> {
                            navController.navigate(R.id.nav_scientific_groups, null, navOptions)
                        }
                        R.id.nav_uci_info -> {
                            navController.navigate(R.id.nav_uci_info, null, navHomeOptions)
                        }
                        R.id.nav_useful_data -> {
                            navController.navigate(R.id.nav_useful_data, null, navOptions)
                        }
                        R.id.nav_feu_secretariat -> {
                            navController.navigate(R.id.nav_feu_secretariat, null, navOptions)
                        }
                        R.id.nav_cloister -> {
                            navController.navigate(R.id.nav_cloister, null, navOptions)
                        }
                    }

                    drawerLayout.removeDrawerListener(this)
                }
            })

            drawerLayout.closeDrawer(navView)
            false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_content_main).navigateUp(appBarConfiguration)
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}