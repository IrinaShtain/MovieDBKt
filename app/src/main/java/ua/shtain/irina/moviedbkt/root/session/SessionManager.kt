package ua.shtain.irina.moviedbkt.root.session

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import ua.shtain.irina.moviedbkt.model.main.User
import javax.inject.Inject

/**
 * Created by Irina Shtain on 08.02.2018.
 */
class SessionManager @Inject constructor(context: Context) : ISessionManager {
    private val mPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val ACCESS_TOKEN = "ACCESS_TOKEN"
    private val SESSION_ID = "SESSION_ID"
    private val USER_NAME = "USER_NAME"
    private val USER_NICK = "USER_NICK"
    private val USER_LANGUAGE = "USER_LANGUAGE"
    private val USER_ID = "USER_ID"
    private val HAS_ADULT_PERM = "HAS_ADULT_PERM"


    override fun getAccessToken() = mPreferences.getString(ACCESS_TOKEN, "")

    override fun getSessionID() = mPreferences.getString(SESSION_ID, "")

    override fun saveAuthToken(token: String) =
            mPreferences.edit()
                    .putString(ACCESS_TOKEN, token)
                    .apply()


    override fun saveSessionID(sessionID: String) =
            mPreferences.edit()
                    .putString(SESSION_ID, sessionID)
                    .apply()

    override fun saveUserData(user: User) {
        mPreferences.edit()
                .putString(USER_NAME, user.name)
                .putString(USER_NICK, user.username)
                .putString(USER_LANGUAGE, user.language)
                .putBoolean(HAS_ADULT_PERM, user.includeAdult)
                .putInt(USER_ID, user.id)
                .apply()
    }

    override fun getUserData(): User {
        return User(mPreferences.getString(USER_NAME, ""),
                mPreferences.getString(USER_NICK, ""),
                mPreferences.getString(USER_LANGUAGE, ""),
                mPreferences.getBoolean(HAS_ADULT_PERM, false),
                mPreferences.getInt(USER_ID, -1)
        )
    }
}