package dev.feryadi.bayu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.feryadi.bayu.model.network.response.UserMutationResponse
import dev.feryadi.bayu.persistence.localpersistence.AuthSharedPreference
import dev.feryadi.bayu.repository.UserMutationRepository
import dev.feryadi.bayu.resourcestate.ResourceState
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.Exception

@Singleton
class UserMutationViewModel @Inject constructor(
    private val userMutationRepository: UserMutationRepository,
    private val authSharedPreference: AuthSharedPreference,
) : ViewModel() {

    val userMutations = MutableLiveData<List<UserMutationResponse>>(listOf())

    fun getUserMutations(): LiveData<ResourceState<List<UserMutationResponse>, Exception>> {
        return liveData<ResourceState<List<UserMutationResponse>, Exception>>(Dispatchers.IO) {
            emit(ResourceState.loading())
            try {
                val token = authSharedPreference.getToken()
                val userId = authSharedPreference.getUserId()
                val response = userMutationRepository.getUserMutations("Bearer $token", userId)

                userMutations.postValue(response.data!!)

                emit(ResourceState.success(response.data))
            } catch (exception: Exception) {
                emit(ResourceState.error(exception))
            }
        }
    }
}