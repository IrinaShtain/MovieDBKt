package ua.shtain.irina.moviedbkt.root.session

import ua.shtain.irina.moviedbkt.model.main.User

/**
 * Created by Irina Shtain on 08.02.2018.
 */
interface ISessionManager {
    fun getAccessToken(): String
    fun getSessionID(): String
    fun saveAuthToken(token: String)
    fun saveSessionID(sessionID: String)
    fun saveUserData(user: User)
    fun getUserData(): User
}