package ua.shtain.irina.moviedbkt.domain

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.LoginSession
import ua.shtain.irina.moviedbkt.model.LoginToken
import ua.shtain.irina.moviedbkt.root.network.servises.LoginService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.login.LoginContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 09.02.2018.
 */
class LoginRepository @Inject constructor(loginService: LoginService, helper: SchedulerHelper) : LoginContract.LoginModel {
    val loginServ = loginService
    val mHelper = helper


    override fun getToken(): Observable<LoginToken> = mHelper.getNetworkObservable(loginServ.getToken())

    override fun getSessionID(username: String, password: String, request_token: String): Observable<LoginSession> = mHelper
            .getNetworkObservable(loginServ.validateToken(username, password, request_token)
                    .concatMap { response -> loginServ.getSessionId(response.requestToken) })
}