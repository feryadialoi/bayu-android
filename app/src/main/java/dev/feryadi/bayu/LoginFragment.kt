package dev.feryadi.bayu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.feryadi.bayu.databinding.FragmentLoginBinding
import dev.feryadi.bayu.model.network.request.LoginRequest
import dev.feryadi.bayu.model.network.response.LoginResponse
import dev.feryadi.bayu.persistence.localpersistence.AuthSharedPreference
import dev.feryadi.bayu.resourcestate.State
import dev.feryadi.bayu.validations.Validator
import dev.feryadi.bayu.viewmodel.AuthViewModel
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var authSharedPreference: AuthSharedPreference

    private lateinit var viewBinding: FragmentLoginBinding

    companion object {
        @JvmStatic
        private val TAG = "LoginFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentLoginBinding.inflate(inflater, container, false)

        initView()

        return viewBinding.root
    }

    private fun initView() {
        initButtonLogin()
        toggleShowProgressBar(false)
    }

    private fun initButtonLogin() {
        viewBinding.btnLoginLogin.setOnClickListener {
            login()
        }
    }

    private fun toggleShowProgressBar(isShow: Boolean) {
        if (isShow) {
            viewBinding.pbLogin.visibility = View.VISIBLE
        } else {
            viewBinding.pbLogin.visibility = View.GONE
        }
    }

    private fun login() {
        resetFormError()

        val username = viewBinding.inputLoginUsername.editText?.text.toString()
        val password = viewBinding.inputLoginPassword.editText?.text.toString()

        validator.validate<LoginRequest>(
            data = LoginRequest(username, password),
            onValid = { loginRequest ->

                toggleShowProgressBar(true)

                authViewModel.login(loginRequest)
                    .observe(requireActivity()) {
                        when (it.state) {
                            State.LOADING -> {
                                loginLoading()
                            }
                            State.SUCCESS -> {
                                it.data?.let { data -> loginSuccess(data) }
                            }
                            State.ERROR -> {
                                it.error?.let { error -> loginError(error) }
                            }
                        }
                    }

            },
            onError = { errors ->
                errors.forEach { validationError ->
                    when (validationError.name) {
                        "username" -> {
                            viewBinding.inputLoginUsername.error = validationError.textError
                            viewBinding.inputLoginUsername.errorIconDrawable = null
                        }
                        "password" -> {
                            viewBinding.inputLoginPassword.error = validationError.textError
                            viewBinding.inputLoginPassword.errorIconDrawable = null
                        }
                    }
                }
            }
        )
    }

    private fun loginSuccess(loginResponse: LoginResponse) {
        Log.i(TAG, "loginSuccess")

        toggleShowProgressBar(false)

        authSharedPreference.setToken(loginResponse.token)
        authSharedPreference.setUserId(loginResponse.userId)

        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
    }

    private fun loginLoading() {
        Log.i(TAG, "loginLoading")
    }

    private fun loginError(error: Exception) {
        error.message?.let { Log.e(TAG, it) }

        toggleShowProgressBar(false)
    }

    private fun resetFormError() {
        viewBinding.inputLoginUsername.error = null
        viewBinding.inputLoginUsername.isErrorEnabled = false

        viewBinding.inputLoginPassword.error = null
        viewBinding.inputLoginPassword.isErrorEnabled = false
    }

}