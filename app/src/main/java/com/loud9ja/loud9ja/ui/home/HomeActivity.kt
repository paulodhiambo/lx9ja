package com.loud9ja.loud9ja.ui.home

import android.Manifest
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
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
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.bumptech.glide.Glide
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.loud9ja.loud9ja.BuildConfig
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ActivityMainBinding
import com.loud9ja.loud9ja.ui.about.AboutUsActivity
import com.loud9ja.loud9ja.ui.authentication.LoginActivity
import com.loud9ja.loud9ja.ui.profile.ProfileActivity
import com.loud9ja.loud9ja.utils.AuthPreference
import com.loud9ja.loud9ja.utils.Constants
import com.loud9ja.loud9ja.utils.DataState
import com.loud9ja.loud9ja.utils.PreferenceHelper
import com.loud9ja.loud9ja.video.JoinActivity
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONException
import org.json.JSONObject
import pub.devrel.easypermissions.EasyPermissions
import kotlin.system.exitProcess


@AndroidEntryPoint
open class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController
    private lateinit var mAuth: FirebaseAuth
    private val homeViewModel: HomeViewModel by viewModels()
    private val AUTH_TOKEN = BuildConfig.AUTH_TOKEN
    private val AUTH_URL = BuildConfig.AUTH_URL
    private val MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 679
    private lateinit var etMeetingId: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        navView = binding.navView
        navView.setupWithNavController(navController)
        drawerLayout = binding.drawerLayout

        val navHeader = navView.getHeaderView(0)
        val themeSwitch = navHeader.findViewById<SwitchMaterial>(R.id.toggle_theme)
        val profile = navHeader.findViewById<CircleImageView>(R.id.profile_imag)
        val name = navHeader.findViewById<TextView>(R.id.txt_name)
        val location = navHeader.findViewById<TextView>(R.id.txt_location)
        name.text = mAuth.currentUser?.displayName
        Glide.with(this)
            .load(mAuth.currentUser?.photoUrl)
            .placeholder(R.drawable.user)
            .into(profile)

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
        val profileImage = bottomSheetDialog.findViewById<ImageView>(R.id.profile_image)
        val user = AuthPreference(this).getAuthDetails()
        Glide.with(this).load("${Constants.IMAGE_PATH}${user?.image}")
            .error(R.drawable.profile_image).into(profileImage!!)
        etMeetingId = bottomSheetDialog.findViewById(R.id.title)!!

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
        homeViewModel.getUserProfile()
        observeProfileDataState(profile, name, location)

    }

    //
    private fun observeProfileDataState(
        profile: CircleImageView,
        name: TextView,
        location: TextView
    ) {
        homeViewModel.profileDataState.observe(this) { data ->
            when (data) {
                is DataState.Success -> {
                    Glide.with(this)
                        .load(data.data.profilePicture)
                        .placeholder(R.drawable.user)
                        .into(profile)
                    name.text = data.data.name
                    location.text = "${data.data.city}"
                }
                is DataState.Loading -> {
                }
                is DataState.Error -> {
                }
            }
        }
    }

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
                getToken(null)
                // startActivity(Intent(this, CreateOrJoinActivity::class.java))
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
                ///
                ///
                ///
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
                mAuth.signOut()
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

    override fun onStart() {
        super.onStart()
        val user = AuthPreference(this).getAuthDetails()
        if (user == null) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.apply {
                this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        val isAvailable = networkInfo != null && networkInfo.isConnected
        if (!isAvailable) {
            Snackbar.make(
                findViewById(R.id.layout), "No Internet Connection",
                Snackbar.LENGTH_LONG
            ).show()
        }
        return isAvailable
    }

    private fun isNullOrEmpty(str: String?): Boolean {
        return "null" == str || "" == str || null == str
    }

    private fun getToken(meetingId: String?) {
        if (!isNetworkAvailable()) {
            return
        }
        if (!isNullOrEmpty(AUTH_TOKEN) && !isNullOrEmpty(AUTH_URL)) {
            Toast.makeText(
                this@HomeActivity,
                "Please Provide only one - either auth_token or auth_url",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (!isNullOrEmpty(AUTH_TOKEN)) {
            if (meetingId == null) {
                createMeeting(AUTH_TOKEN)
            } else {
                joinMeeting(AUTH_TOKEN, meetingId)
            }
            return
        }
        if (!isNullOrEmpty(AUTH_URL)) {
            AndroidNetworking.get("$AUTH_URL/get-token")
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            val token = response.getString("token")
                            if (meetingId == null) {
                                createMeeting(token)
                            } else {
                                joinMeeting(token, meetingId)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }

                    override fun onError(anError: ANError) {
                        anError.printStackTrace()
                        Toast.makeText(
                            this@HomeActivity,
                            anError.errorDetail, Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            return
        }
        Toast.makeText(
            this@HomeActivity,
            "Please Provide auth_token or auth_url", Toast.LENGTH_SHORT
        ).show()
    }


    private fun createMeeting(token: String) {
        val perms = arrayOfNulls<String>(2)
        perms[0] = Manifest.permission.BLUETOOTH
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            perms[1] = Manifest.permission.BLUETOOTH_CONNECT
        }
        if (EasyPermissions.hasPermissions(this, *perms)) {
            AndroidNetworking.post("https://api.videosdk.live/v1/meetings")
                .addHeaders("Authorization", token)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            val meetingId = response.getString("meetingId")
                            val intent = Intent(this@HomeActivity, JoinActivity::class.java)
                            intent.putExtra("token", token)
                            intent.putExtra("meetingId", meetingId)
                            startActivity(intent)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }

                    override fun onError(anError: ANError) {
                        Toast.makeText(
                            this@HomeActivity, anError.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                this, getString(R.string.rationale_location_contacts),
                MY_PERMISSIONS_REQUEST_WRITE_CALENDAR, *perms
            )
        }
    }

    private fun joinMeeting(token: String, meetingId: String) {
        AndroidNetworking.post("https://api.videosdk.live/v1/meetings/$meetingId")
            .addHeaders("Authorization", token)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val intent = Intent(this@HomeActivity, JoinActivity::class.java)
                    intent.putExtra("token", token)
                    intent.putExtra("meetingId", meetingId)
                    startActivity(intent)
                    etMeetingId.text?.clear()
                }

                override fun onError(anError: ANError) {
                    anError.printStackTrace()
                    Toast.makeText(
                        this@HomeActivity, anError.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

}