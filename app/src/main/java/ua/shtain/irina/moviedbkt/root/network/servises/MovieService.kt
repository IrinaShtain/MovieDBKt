package ua.shtain.irina.moviedbkt.root.network.servises

import io.reactivex.Observable
import retrofit2.http.*
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.movie.*
import ua.shtain.irina.moviedbkt.model.movie.genre.GenresResponse
import ua.shtain.irina.moviedbkt.model.star.StarResponse

/**
 * Created by Irina Shtain on 19.02.2018.
 */
interface MovieService {
    @GET("/3/genre/movie/list")
    fun getGenres(): Observable<GenresResponse>

    @GET("/3/list/{list_id}")
    fun getMovies(@Path("list_id") list_id: Int): Observable<MoviesResponse>

    @Headers("content-type: application/json;charset=utf-8")
    @POST("/3/list/{list_id}/remove_item")
    fun deleteMovie(@Path("list_id") list_id: Int,
                    @Body action: ActionRequest): Observable<ResponseMessage>

    @GET("/3/search/movie")
    fun searchMovieByTitle(@Query("query") title: String,
                           @Query("page") page: Int): Observable<SearchMovieResponse>

    @GET("/3/movie/popular")
    fun searchPopularMovies(@Query("page") page: Int): Observable<SearchMovieResponse>

    @GET("/3/movie/now_playing")
    fun searchLatestMovies(@Query("page") page: Int): Observable<SearchMovieResponse>

    @GET("/3/genre/{genre_id}/movies")
    fun searchMovieByGenre(@Path("genre_id") genre_id: Int, @Query("page") page: Int): Observable<SearchMovieResponse>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movie_id: Int): Observable<MovieItem>

    @Headers("content-type: application/json;charset=utf-8")
    @POST("/3/list/{list_id}/add_item")
    fun addMovie(@Path("list_id") list_id: Int,
                 @Body action: ActionRequest): Observable<ResponseMessage>

    @GET("/3/search/person")
    fun searchStar(@Query("query") title: String,
                   @Query("page") page: Int): Observable<StarResponse>


    @Headers("content-type: application/json;charset=utf-8")
    @DELETE("/3/list/{list_id}")
    fun deleteList(@Path("list_id") list_id: Int): Observable<ResponseMessage>

    @Headers("content-type: application/json;charset=utf-8")
    @POST("/3/movie/{movie_id}/rating")
    fun rateMovie(@Path("movie_id") movie_id: Int,
                  @Body rateRequest: RateRequest): Observable<ResponseMessage>

    @Headers("content-type: application/json;charset=utf-8")
    @POST("/3/account/{account_id}/favorite")
    fun addToFavoriteMovie(@Body favoriteRequest: FavoriteRequest): Observable<ResponseMessage>

    @Headers("content-type: application/json;charset=utf-8")
    @POST("/3/account/{account_id}/watchlist")
    fun addToWatchListMovie(@Body watchRequest: WatchRequest): Observable<ResponseMessage>
}
