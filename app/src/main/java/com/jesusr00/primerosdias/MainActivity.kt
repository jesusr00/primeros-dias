package com.jesusr00.primerosdias

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_direction_council, R.id.nav_events, R.id.nav_faculty_info,
                R.id.nav_feu_movements, R.id.nav_guide_teachers, R.id.nav_map, R.id.nav_scientific_groups,
                R.id.nav_uci_info, R.id.nav_useful_data
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val navBuilder = NavOptions.Builder()
        navBuilder.setEnterAnim(R.anim.fade_in).setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in).setPopExitAnim(R.anim.fade_out)

        navView.setNavigationItemSelectedListener { menuItem ->

            drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
                override fun onDrawerStateChanged(newState: Int) {}

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

                override fun onDrawerOpened(drawerView: View) {}

                override fun onDrawerClosed(drawerView: View) {

                    when (menuItem.itemId) {
                        R.id.nav_home -> {
                            navController.navigate(R.id.nav_home, null, navBuilder.build())
                        }
                        R.id.nav_direction_council -> {
                            navController.navigate(R.id.nav_direction_council, null, navBuilder.build())
                        }
                        R.id.nav_events -> {
                            navController.navigate(R.id.nav_events, null, navBuilder.build())
                        }
                        R.id.nav_faculty_info -> {
                            navController.navigate(R.id.nav_faculty_info, null, navBuilder.build())
                        }
                        R.id.nav_feu_movements -> {
                            navController.navigate(R.id.nav_feu_movements, null, navBuilder.build())
                        }
                        R.id.nav_guide_teachers -> {
                            navController.navigate(R.id.nav_guide_teachers, null, navBuilder.build())
                        }
                        R.id.nav_map -> {
                            navController.navigate(R.id.nav_map, null, navBuilder.build())
                        }
                        R.id.nav_scientific_groups -> {
                            navController.navigate(R.id.nav_scientific_groups, null, navBuilder.build())
                        }
                        R.id.nav_uci_info -> {
                            navController.navigate(R.id.nav_uci_info, null, navBuilder.build())
                        }
                        R.id.nav_useful_data -> {
                            navController.navigate(R.id.nav_useful_data, null, navBuilder.build())
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

    private fun fade(viewIn: View, viewOut: View, animateViewOut: Boolean = true) {

        val fadeDuration = 200L

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        viewIn.alpha = 0f
        viewIn.visibility = View.VISIBLE
        viewIn.bringToFront()

        // Animate the in view to 100% opacity, and clear any animation
        // listener set on the view.
        viewIn.animate()
            .alpha(1f)
            .setDuration(fadeDuration)
            .setListener(null)

        // Animate the out view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        viewOut.animate()
            .alpha(0f)
            .setDuration(if (animateViewOut) fadeDuration else 0)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    viewOut.visibility = View.GONE
                }
            })

    }
}