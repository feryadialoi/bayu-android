package dev.feryadi.bayu.persistence.localpersistence

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthSharedPreference @Inject constructor(
    @ApplicationContext context: Context
) {
    companion object {
        private const val TOKEN = "token"
        private const val USER_ID = "userId"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun getToken(): String {
        return preferences.getString(TOKEN, "")!!
    }

    fun setToken(token: String) {
        preferences.edit().putString(TOKEN, token).apply()
    }

    fun removeToken() {
        preferences.edit().remove(TOKEN).apply()
    }

    fun getUserId(): Long {
        return preferences.getLong(USER_ID, 0)
    }

    fun setUserId(userId: Long) {
        preferences.edit().putLong(USER_ID, userId).apply()
    }

    fun removeUserId() {
        preferences.edit().remove(USER_ID)
    }
}