package ua.shtain.irina.moviedbkt.root.network.servises

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ua.shtain.irina.moviedbkt.model.user.LoginSession
import ua.shtain.irina.moviedbkt.model.user.LoginToken
import ua.shtain.irina.moviedbkt.other.Constants

/**
 * Created by Irina Shtain on 09.02.2018.
 */
interface LoginService {

    @GET(Constants.GET_USER_TOKEN)
    fun getToken(): Observable<LoginToken>

    @GET(Constants.GET_VALIDATED_USER_TOKEN)
    fun validateToken(@Query("username") username: String,
                      @Query("password") password: String,
                      @Query("request_token") request_token: String): Observable<LoginToken>

    @GET(Constants.GET_USER_SESSION_ID)
    fun getSessionId(@Query("request_token") request_token: String): Observable<LoginSession>
}
