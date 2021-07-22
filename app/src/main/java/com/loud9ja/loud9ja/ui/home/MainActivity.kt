package com.loud9ja.loud9ja.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ActivityMainBinding
import com.loud9ja.loud9ja.ui.livestream.LiveStreamActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        val navView: NavigationView = binding.navView
        navView.setupWithNavController(navController)
        val drawerLayout: DrawerLayout = binding.drawerLayout

        binding.drawerButton.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.go_live_bottom_sheet)

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

        binding.root.findViewById<FloatingActionButton>(R.id.live_fab).setOnClickListener {
            showBottomDialog()
        }
        cancelLiveSession()
        proceedLiveSession()

    }

    //

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun showBottomDialog() {
        bottomSheetDialog.show()
    }

    private fun cancelLiveSession() {
        bottomSheetDialog.findViewById<MaterialButton>(R.id.cancel_sheet)
            ?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
    }

    private fun proceedLiveSession() {
        bottomSheetDialog.findViewById<MaterialButton>(R.id.proceed_sheet)
            ?.setOnClickListener {
                startActivity(Intent(this, LiveStreamActivity::class.java))
                bottomSheetDialog.dismiss()
            }
    }
}