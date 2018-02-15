package ua.shtain.irina.moviedbkt.root.network.servises

import io.reactivex.Observable
import retrofit2.http.*
import ua.shtain.irina.moviedbkt.model.lists.CreatedListsData
import ua.shtain.irina.moviedbkt.model.lists.NewListRequest
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.main.User
import ua.shtain.irina.moviedbkt.other.Constants

/**
 * Created by Irina Shtain on 13.02.2018.
 */
interface ProfileService {

    @GET(Constants.GET_USER_ACCOUNT)
    fun getDetails(@Query("session_id") sessionId: String): Observable<User>

    @GET("/3/account/{account_id}/lists")
    fun getLists(@Path("account_id") account_id: Int,
                 @Query("page") page: Int): Observable<CreatedListsData>


    @Headers("content-type: application/json;charset=utf-8")
    @POST("/3/list")
    fun createList(@Body request: NewListRequest): Observable<ResponseMessage>

    @Headers("content-type: application/json;charset=utf-8")
    @DELETE("/3/list/{list_id}")
    fun deleteList(@Path("list_id") list_id: Int): Observable<ResponseMessage>

}
