package dev.feryadi.bayu.apiservice

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.runner.RunWith
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dev.feryadi.bayu.model.network.request.LoginRequest
import dev.feryadi.bayu.repository.AuthRepository
import dev.feryadi.bayu.viewmodel.AuthViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(manifest=Config.NONE, application = HiltTestApplication::class)
class AuthApiServiceTest {
    @Inject
    lateinit var authApiService: AuthApiService

    @Inject
    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var authViewModel: AuthViewModel

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun loginTest() {
        runBlocking {
            val response = authApiService.login(LoginRequest("feryadialoi", "password"))
            println(response)
        }
    }

    @Test
    fun authRepositoryTest() {
        runBlocking {
            val response = authRepository.login(LoginRequest("feryadialoi", "password"))
            println(response)
        }
    }

    @Test
    fun authViewModelTest() {
//        authViewModel.login(LoginRequest("feryadialoi", "password"))
//            .observeForever {
//                println(it)
//            }
        authViewModel.mockLogin().observeForever {
            println(it)
        }
    }
}