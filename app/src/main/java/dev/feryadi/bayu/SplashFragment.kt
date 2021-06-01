package dev.feryadi.bayu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.feryadi.bayu.databinding.FragmentSplashBinding
import dev.feryadi.bayu.persistence.localpersistence.AuthSharedPreference
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var authSharedPreference: AuthSharedPreference

    lateinit var viewBinding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSplashBinding.inflate(inflater, container, false)

        // storeExpiredCredential()

        initView()

        splashOff()

        return viewBinding.root
    }

    private fun initView() {

    }

    private fun storeExpiredCredential() {
        authSharedPreference.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmZXJ5YWRpYWxvaSIsInRva2VuSWQiOiIyNDNkN2IwMS1iMjdkLTRhOTMtODg0My1jZjdlNGE1OGMyMGIiLCJleHAiOjE2MjI1NjMzODksImlhdCI6MTYyMjU1OTc4OSwidXNlcklkIjoxfQ.SFoTXzbVmdYxCVE6GkrtW19TXYy3VOAq4sDGdL5xSgLCycbs9SNkmaFjVv2_F3_ngtDC2ETcvat4_e-JFDEqtA")
        authSharedPreference.setUserId(1)
    }

    private fun splashOff() {
        val token = authSharedPreference.getToken()

        lifecycleScope.launch {
            delay(2000)
            if (token == "") {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
    }
}