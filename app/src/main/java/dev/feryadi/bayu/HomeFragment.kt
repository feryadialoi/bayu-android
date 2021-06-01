package dev.feryadi.bayu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import dev.feryadi.bayu.databinding.FragmentHomeBinding
import dev.feryadi.bayu.model.network.response.BalanceResponse
import dev.feryadi.bayu.persistence.localpersistence.AuthSharedPreference
import dev.feryadi.bayu.resourcestate.State
import dev.feryadi.bayu.viewmodel.UserBalanceViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var userBalanceViewModel: UserBalanceViewModel

    @Inject
    lateinit var authSharedPreference: AuthSharedPreference

    lateinit var viewBinding: FragmentHomeBinding

    companion object {
        @JvmStatic
        private val TAG = "HomeFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)

        getUserBalance()

        return viewBinding.root
    }

    private fun initView() {
        userBalanceSubscribe()
    }

    private fun userBalanceSubscribe() {

    }

    private fun getUserBalance() {
        val userId = authSharedPreference.getUserId()
        userBalanceViewModel.getUserBalance(userId).observe(requireActivity()) {
            when (it.state) {
                State.LOADING -> {
                    getUserBalanceLoading()
                }
                State.SUCCESS -> {
                    it.data?.let { balanceResponse -> getUserBalanceSuccess(balanceResponse) }
                }
                State.ERROR -> {
                    it.error?.let { error -> getUserBalanceError(error) }
                }
            }
        }
    }

    private fun getUserBalanceLoading() {

    }

    private fun getUserBalanceSuccess(balanceResponse: BalanceResponse) {
        Log.i(TAG, "getUserBalanceSuccess")

        viewBinding.pbHome.visibility = View.GONE

        viewBinding.tvHomeBalanceAmount.text = balanceResponse.balance.toString()
    }

    private fun getUserBalanceError(error: Exception) {
        Log.e(TAG, error.toString())

        viewBinding.pbHome.visibility = View.GONE
    }
}