package dev.feryadi.bayu

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dev.feryadi.bayu.component.AlertDialogConfirm
import dev.feryadi.bayu.databinding.ActivityMainBinding
import dev.feryadi.bayu.databinding.AlertDialogConfirmLayoutBinding

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var navController: NavController

    companion object {
        @JvmStatic
        private val TAG = "MainActivity"
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initView()

        initBottomNavBar()

        navControllerListener()

    }

    private fun initView() {
        navController = findNavController(R.id.nav_host_fragment)
    }

    private fun navControllerListener() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            toggleShowBottomNavBar(destination)
        }
    }

    private fun toggleShowBottomNavBar(destination: NavDestination) {

        when (destination.id) {
            R.id.homeFragment -> {
                viewBinding.bottomNavView.visibility = View.VISIBLE
            }
            R.id.dashboardFragment -> {
                viewBinding.bottomNavView.visibility = View.VISIBLE
            }
            R.id.scanFragment -> {
                viewBinding.bottomNavView.visibility = View.VISIBLE
            }
            R.id.notificationsFragment -> {
                viewBinding.bottomNavView.visibility = View.VISIBLE
            }
            R.id.profileFragment -> {
                viewBinding.bottomNavView.visibility = View.VISIBLE
            }
            else -> {
                viewBinding.bottomNavView.visibility = View.GONE
            }
        }

    }

    private fun initBottomNavBar() {

        viewBinding.bottomNavView.setupWithNavController(navController)

        viewBinding.bottomNavView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.navigation_home -> {
                    Log.i(TAG, "Home")
                    navController.navigate(NavGraphDirections.actionGlobalHomeFragment())
                }
                R.id.navigation_dashboard -> {
                    Log.i(TAG, "Dashboard")
                    navController.navigate(NavGraphDirections.actionGlobalDashboardFragment())
                }
                R.id.navigation_scan -> {
                    Log.i(TAG, "Scan")
                    navController.navigate(NavGraphDirections.actionGlobalScanFragment())
                }
                R.id.navigation_notifications -> {
                    Log.i(TAG, "Notifications")
                    navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment())
                }
                R.id.navigation_profile -> {
                    Log.i(TAG, "Profile")
                    navController.navigate(NavGraphDirections.actionGlobalProfileFragment())
                }
            }

            true
        }

    }

    override fun handleUnauthorizedEvent() {
        runOnUiThread {
            AlertDialogConfirm(AlertDialogConfirmLayoutBinding.inflate(layoutInflater))
                .setTitle(resources.getString(R.string.main_session_expired))
                .setButtonTitle("Logout")
                .setConfirmOnClickListener { logout() }
                .setCancelable(false)
                .create()
                .show()
        }
    }
}