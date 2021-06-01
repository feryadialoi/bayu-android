package dev.feryadi.bayu

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dev.feryadi.bayu.model.UnauthorizedEvent
import dev.feryadi.bayu.persistence.localpersistence.AuthSharedPreference
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

open class BaseActivity : AppCompatActivity() {

    private lateinit var authSharedPreference: AuthSharedPreference

    companion object {
        @JvmStatic
        private val TAG = "BaseActivity"
    }

    override fun onStart() {

        authSharedPreference = AuthSharedPreference(this)

        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onUnauthorizedEvent(unauthorizedEvent: UnauthorizedEvent) {
        handleUnauthorizedEvent()
    }

    protected open fun handleUnauthorizedEvent() {
        Log.e(TAG, "handleUnauthorizedEvent")
        logout()
    }

    protected fun logout() {
        authSharedPreference.removeToken()
        authSharedPreference.removeUserId()

        runOnUiThread {
            val navController = findNavController(R.id.nav_host_fragment)
            navController.popBackStack()
            navController.navigate(NavGraphDirections.actionGlobalLoginFragment())
        }
    }

}