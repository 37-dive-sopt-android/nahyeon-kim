package com.sopt.dive.data

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class UserPreferences @Inject constructor(
    private val prefs: SharedPreferences
) {
    fun setUser(username: String, password: String) {
        prefs.edit {
            putString(KEY_USERNAME, username)
            putString(KEY_PASSWORD, password)
            putBoolean(KEY_IS_LOGGED_IN, true)
        }
    }

    fun setUserId(userId: Long) {
        prefs.edit {
            putLong(KEY_USER_ID, userId)
        }
    }

    fun getUserId(): Long {
        return prefs.getLong(KEY_USER_ID, -1L)
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun signOut() {
        prefs.edit {
            clear()
        }
    }

    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_PASSWORD = "password"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}