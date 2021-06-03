package dev.feryadi.bayu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.feryadi.bayu.adapter.MutationAdapter
import dev.feryadi.bayu.adapter.MutationAdapterImpl
import dev.feryadi.bayu.databinding.FragmentHistoryBinding
import dev.feryadi.bayu.model.network.response.UserMutationResponse
import dev.feryadi.bayu.resourcestate.State
import dev.feryadi.bayu.viewmodel.UserMutationViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    @Inject
    lateinit var userMutationViewModel: UserMutationViewModel

    private lateinit var viewBinding: FragmentHistoryBinding

    private lateinit var mutationAdapter: MutationAdapter

    companion object {
        @JvmStatic
        private val TAG = "HistoryFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHistoryBinding.inflate(inflater, container, false)

        initView()

        getUserMutations()

        observeUserMutations()

        return viewBinding.root
    }

    private fun initView() {
        initRecyclerViewMutations()
    }

    private fun initRecyclerViewMutations() {
        mutationAdapter = MutationAdapterImpl(listOf())

        viewBinding.rvHistoryMutations.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.rvHistoryMutations.adapter = mutationAdapter
    }

    private fun observeUserMutations() {
        userMutationViewModel.userMutations.observe(requireActivity()) {
            mutationsAdapterListener(it)
        }
    }

    private fun mutationsAdapterListener(mutations: List<UserMutationResponse>) {
        mutationAdapter.apply {
            addMutations(mutations)
            notifyDataSetChanged()
        }
    }

    private fun getUserMutations() {
        userMutationViewModel.getUserMutations().observe(requireActivity()) {
            when (it.state) {
                State.LOADING -> {
                    getUserMutationsLoading()
                }
                State.SUCCESS -> {
                    getUserMutationsSuccess()
                }
                State.ERROR -> {
                    it.error?.let { error -> getUserMutationsError(error) }
                }
            }
        }
    }

    private fun getUserMutationsLoading() {
        Log.i(TAG, "getUserMutationsLoading")
    }

    private fun getUserMutationsSuccess() {
        Log.i(TAG, "getUserMutationsSuccess")
    }

    private fun getUserMutationsError(exception: Exception) {
        Log.e(TAG, exception.toString())
    }
}