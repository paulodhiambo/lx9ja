package com.loud9ja.loud9ja.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomAppBar: BottomAppBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.root.findViewById<ConstraintLayout>(R.id.discussion_menuu).setOnClickListener {
            navController.navigate(R.id.action_to_FirstFragment)

        }
        binding.root.findViewById<ConstraintLayout>(R.id.report_menuu).setOnClickListener {
            navController.navigate(R.id.action_to_SecondFragment)

        }

        binding.root.findViewById<ConstraintLayout>(R.id.group_menuu).setOnClickListener {
            navController.navigate(R.id.action_to_GroupFragment)

        }

        binding.root.findViewById<ConstraintLayout>(R.id.sos_menuu).setOnClickListener {
            navController.navigate(R.id.action_to_SOSFragment)

        }


//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

    //

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}