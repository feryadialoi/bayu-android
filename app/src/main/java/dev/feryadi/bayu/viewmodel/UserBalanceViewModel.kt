package dev.feryadi.bayu.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.feryadi.bayu.model.network.response.BalanceResponse
import dev.feryadi.bayu.persistence.localpersistence.AuthSharedPreference
import dev.feryadi.bayu.repository.UserBalanceRepository
import dev.feryadi.bayu.resourcestate.ResourceState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserBalanceViewModel @Inject constructor(
    private val userBalanceRepository: UserBalanceRepository,
    private val authSharedPreference: AuthSharedPreference
) : ViewModel() {
    companion object {
        @JvmStatic
        private val TAG = "UserBalanceViewModel"
    }
    fun getUserBalance(userId: Long): LiveData<ResourceState<BalanceResponse, Exception>> {
        return liveData<ResourceState<BalanceResponse, Exception>>() {
            emit(ResourceState.loading())
            try {
                val token = authSharedPreference.getToken()
                val response = userBalanceRepository.getUserBalance("Bearer $token", userId)
                emit(ResourceState.success(response.data))
            } catch (exception: Exception) {
                emit(ResourceState.error(exception))
            }
        }
    }
}