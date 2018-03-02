package ua.shtain.irina.moviedbkt.root.network.servises

import io.reactivex.Observable
import retrofit2.http.*
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.movie.FavoriteRequest
import ua.shtain.irina.moviedbkt.model.movie.WatchRequest
import ua.shtain.irina.moviedbkt.model.tv.TVShowItem

/**
 * Created by Irina Shtain on 01.03.2018.
 */
interface TvService {
    @GET("/3/tv/{tv_id}")
    fun getTvDetails(@Path("tv_id") tv_id: Int): Observable<TVShowItem>

    @Headers("content-type: application/json;charset=utf-8")
    @POST("/3/account/{account_id}/favorite")
    fun addToFavoriteMovie(@Body favoriteRequest: FavoriteRequest): Observable<ResponseMessage>

    @Headers("content-type: application/json;charset=utf-8")
    @POST("/3/account/{account_id}/watchlist")
    fun addToWatchListMovie(@Body watchRequest: WatchRequest): Observable<ResponseMessage>
}