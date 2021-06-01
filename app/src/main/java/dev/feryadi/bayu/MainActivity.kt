package dev.feryadi.bayu

import android.os.Bundle
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.feryadi.bayu.component.AlertDialogConfirm
import dev.feryadi.bayu.databinding.ActivityMainBinding
import dev.feryadi.bayu.databinding.AlertDialogConfirmLayoutBinding

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var viewBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
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