package ua.shtain.irina.moviedbkt.root.network.servises

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.model.star.StarDetails
import ua.shtain.irina.moviedbkt.model.star.StarImageResponse
import ua.shtain.irina.moviedbkt.model.star.StarResponse

/**
 * Created by Irina Shtain on 01.03.2018.
 */
interface StarService {
    @GET("/3/search/person")
    fun searchStar(@Query("query") title: String,
                   @Query("page") page: Int): Observable<StarResponse>

    @GET("/3/person/{person_id}")
    fun getStarDetails(@Path("person_id") person_id: Int): Observable<StarDetails>

    @GET("/3/person/{person_id}/images")
    fun getStarImages(@Path("person_id") person_id: Int): Observable<StarImageResponse>

}