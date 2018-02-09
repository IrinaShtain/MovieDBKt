package ua.shtain.irina.moviedbkt.root.session

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import javax.inject.Inject

/**
 * Created by Irina Shtain on 08.02.2018.
 */
class SharedPrefManager @Inject constructor(context: Context) : ISessionManager {
    private val mPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val ACCESS_TOKEN = "ACCESS_TOKEN"
    private val SESSION_ID = "SESSION_ID"


    override fun getAccessToken(): String = mPreferences.getString(ACCESS_TOKEN, "")

    override fun getSessionID(): String =
            mPreferences.getString(SESSION_ID, "")

    override fun saveAuthToken(token: String) =
            mPreferences.edit()
                    .putString(ACCESS_TOKEN, token)
                    .apply()


    override fun saveSessionID(sessionID: String) =
            mPreferences.edit()
                    .putString(SESSION_ID, sessionID)
                    .apply()

}