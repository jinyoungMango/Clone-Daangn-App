package com.project.daangn

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.project.daangn.databinding.ActivityMainBinding
import com.project.daangn.util.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        initBottomNavigation()
        initToolbar()
        initListener()
    }

    private fun initBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.toolbar.imageButtonMypage.visibility = View.GONE
//                    binding.bottomNavigationView.visibility = View.VISIBLE
                }

                R.id.communityFragment -> {
                    binding.toolbar.imageButtonMypage.visibility = View.VISIBLE
                }

            }
        }
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun initListener() {
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_rotate)

        binding.toolbar.layoutLeft.setOnClickListener {
            binding.toolbar.imageButtonToggle.startAnimation(rotateAnimation)
            binding.toolbar.imageButtonToggle.rotation = 180f
        }

        binding.toolbar.imageButtonSearch.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_searchFragment)
        }

        binding.toolbar.imageButtonNotification.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_notificationFragment)
        }
    }
}

