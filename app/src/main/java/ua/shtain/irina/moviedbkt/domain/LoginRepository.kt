package ua.shtain.irina.moviedbkt.domain

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.LoginSession
import ua.shtain.irina.moviedbkt.model.LoginToken
import ua.shtain.irina.moviedbkt.root.network.servises.LoginService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.login.content.LoginContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 09.02.2018.
 */
class LoginRepository @Inject constructor(loginService: LoginService, helper: SchedulerHelper) : LoginContract.LoginModel {
    val service = loginService
    val mHelper = helper


    override fun getToken(): Observable<LoginToken> = mHelper.getNetworkObservable(service.getToken())

    override fun getSessionID(username: String, password: String, request_token: String): Observable<LoginSession> = mHelper
            .getNetworkObservable(service.validateToken(username, password, request_token)
                    .concatMap { response -> service.getSessionId(response.requestToken) })
}