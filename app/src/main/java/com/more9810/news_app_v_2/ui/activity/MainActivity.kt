package com.more9810.news_app_v_2.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.more9810.news_app_v_2.R
import com.more9810.news_app_v_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var listener: NavController.OnDestinationChangedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolBar)

        navController =
            (supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment).navController
        val drawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        binding.myNavView.setupWithNavController(navController)


        setupActionBarWithNavController(navController, appBarConfiguration)

        listener =
            NavController.OnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.homeFragment -> {
                        supportActionBar?.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                this, R.color.black
                            )
                        )
                    }

                    R.id.newsFragment -> {
                        supportActionBar?.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                this, R.color.black
                            )
                        )
//                        val toolbar = binding.toolBar
//                        val params = toolbar.layoutParams as AppBarLayout.LayoutParams
//                        params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
//                                AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
//                        toolbar.layoutParams = params
                    }

                    R.id.settingFragment -> {
                        supportActionBar?.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                this, R.color.m3
                            )
                        )
                    }
                }
            }

    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}