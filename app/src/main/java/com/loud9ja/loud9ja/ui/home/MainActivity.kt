package com.loud9ja.loud9ja.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ActivityMainBinding
import com.loud9ja.loud9ja.ui.about.AboutUsActivity
import com.loud9ja.loud9ja.ui.authentication.LoginActivity
import com.loud9ja.loud9ja.ui.livestream.LiveStreamActivity
import com.loud9ja.loud9ja.ui.profile.ProfileActivity
import com.loud9ja.loud9ja.utils.PreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess


@AndroidEntryPoint
open class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        navView = binding.navView
        navView.setupWithNavController(navController)
        drawerLayout = binding.drawerLayout

        val theme = PreferenceHelper(this).getTheme()
        Log.d("Theme=======>", "onCreate: $theme")

        val navHeader = navView.getHeaderView(0)
        val themeSwitch = navHeader.findViewById<SwitchMaterial>(R.id.toggle_theme)

        themeSwitch.isChecked = theme != 1

        if (theme == 0) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        themeSwitch.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    PreferenceHelper(this).saveTheme(1)
                    Log.d("Theme Change=======>", "onCreate: 1")
                }
                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    PreferenceHelper(this).saveTheme(0)
                    Log.d("Theme Change=======>", "onCreate: 0")
                }
            }
        }


        navView.setNavigationItemSelectedListener(this)
        navView.bringToFront()

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_dashboard -> {
                navController.navigate(R.id.action_to_DashboardFragment)
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.nav_discussions -> {
                navController.navigate(R.id.action_to_FirstFragment)
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.nav_reports -> {
                navController.navigate(R.id.action_to_SecondFragment)
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.nav_groups -> {
                navController.navigate(R.id.action_to_GroupFragment)
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.nav_go_live -> {
                showBottomDialog()
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.nav_sos -> {
                navController.navigate(R.id.action_to_SOSFragment)
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.nav_polls -> {
                navController.navigate(R.id.action_to_PollsFragment)
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.nav_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.nav_about_us -> {
                startActivity(Intent(this, AboutUsActivity::class.java))
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.nav_logout -> {
                drawerLayout.closeDrawer(GravityCompat.START)
                val intent = Intent(this, LoginActivity::class.java)
                    .apply {
                        this.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }
                startActivity(intent)
                true
            }
            else -> false
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun exitByBackKey() {
        val alertBox = AlertDialog.Builder(this)
            .setMessage("Do you want to exit application?")
            .setPositiveButton("Yes") { _, _ ->
                finishAffinity()
                exitProcess(0)
            }
            .setNegativeButton(
                "No"
            ) // do something when the button is clicked
            { dialog, _ -> dialog.dismiss() }
            .show()
    }
}