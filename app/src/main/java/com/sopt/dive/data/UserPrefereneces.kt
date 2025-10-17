package com.sopt.dive.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

data class UserInfo(
    val id: String = "",
    val password: String = "",
    val nickname: String = "",
    val mbti: String = ""
)

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "user_prefs"
        private const val KEY_ID = "id"
        private const val KEY_PASSWORD = "password"
        private const val KEY_NICKNAME = "nickname"
        private const val KEY_MBTI = "mbti"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    fun registerUser(id: String, password: String, nickname: String, mbti: String) {
        prefs.edit {
            putString(KEY_ID, id)
            putString(KEY_PASSWORD, password)
            putString(KEY_NICKNAME, nickname)
            putString(KEY_MBTI, mbti)
            putBoolean(KEY_IS_LOGGED_IN, false)
        }
    }

    fun signIn(id: String, password: String): Boolean {
        val savedId = prefs.getString(KEY_ID, "") ?: ""
        val savedPassword = prefs.getString(KEY_PASSWORD, "") ?: ""

        return if (id == savedId && password == savedPassword) {
            prefs.edit {
                putBoolean(KEY_IS_LOGGED_IN, true)
            }
            true
        } else {
            false
        }
    }

    fun getUserInfo(): UserInfo {
        return UserInfo(
            id = prefs.getString(KEY_ID, "") ?: "",
            password = prefs.getString(KEY_PASSWORD, "") ?: "",
            nickname = prefs.getString(KEY_NICKNAME, "") ?: "",
            mbti = prefs.getString(KEY_MBTI, "") ?: ""
        )
    }

    fun isSignedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun signOut() {
        prefs.edit {
            putBoolean(KEY_IS_LOGGED_IN, false)
        }
    }

}