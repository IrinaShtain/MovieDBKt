package ua.shtain.irina.moviedbkt.root.network.servises

import io.reactivex.Observable
import retrofit2.http.*
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.movie.review.ReviewResponse
import ua.shtain.irina.moviedbkt.model.requests.FavoriteRequest
import ua.shtain.irina.moviedbkt.model.requests.WatchRequest
import ua.shtain.irina.moviedbkt.model.tv.SearchTvShowResponse
import ua.shtain.irina.moviedbkt.model.tv.TvShowItem

/**
 * Created by Irina Shtain on 01.03.2018.
 */
interface TvService {
    @GET("/3/tv/{tv_id}")
    fun getTvDetails(@Path("tv_id") tv_id: Int): Observable<TvShowItem>

    @Headers("content-type: application/json;charset=utf-8")
    @POST("/3/account/{account_id}/favorite")
    fun markToFavoriteMovie(@Body favoriteRequest: FavoriteRequest): Observable<ResponseMessage>

    @Headers("content-type: application/json;charset=utf-8")
    @POST("/3/account/{account_id}/watchlist")
    fun markToWatchListMovie(@Body watchRequest: WatchRequest): Observable<ResponseMessage>

    @GET("/3/tv/top_rated")
    fun searchTopRatedShows(@Query("page") page: Int): Observable<SearchTvShowResponse>

    @GET("/3/tv/popular")
    fun searchPopularShows(@Query("page") page: Int): Observable<SearchTvShowResponse>

    @GET("/3/account/{account_id}/watchlist/tv")
    fun getWatchlistShows(@Query("page") page: Int): Observable<SearchTvShowResponse>

    @GET("/3/account/{account_id}/favorite/tv")
    fun getFavoriteaShows(@Query("page") page: Int): Observable<SearchTvShowResponse>

    @GET("/3/tv/airing_today")
    fun searchLatestShows(@Query("page") page: Int): Observable<SearchTvShowResponse>

    @GET("/3/tv/on_the_air")
    fun searchTvOnTheAir(@Query("page") page: Int): Observable<SearchTvShowResponse>

    @GET("/3/tv/{tv_id}/recommendations")
    fun getTvShowRecommendations(@Path("tv_id") tv_id: Int, @Query("page") page: Int): Observable<SearchTvShowResponse>

    @GET("/3/tv/{tv_id}/reviews")
    fun getTvShowReviews(@Path("tv_id") tv_id: Int, @Query("page") page: Int): Observable<ReviewResponse>
}