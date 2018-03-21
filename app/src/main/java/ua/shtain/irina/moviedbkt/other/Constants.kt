package ua.shtain.irina.moviedbkt.other

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import ua.shtain.irina.moviedbkt.R

/**
 * Created by Irina Shtain on 09.02.2018.
 */
object Constants {
    const val CLICK_DELAY: Long = 600
    const val TIMEOUT: Long = 30 //seconds
    const val TIMEOUT_READ: Long = 30 //seconds
    const val TIMEOUT_WRITE: Long = 60 //seconds

    const val BASE_URL = "https://api.themoviedb.org"

    /*----------------- API ------------------*/
    const val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"
    const val GET_USER_TOKEN = "/3/authentication/token/new"
    const val GET_VALIDATED_USER_TOKEN = "/3/authentication/token/validate_with_login"
    const val GET_USER_SESSION_ID = "3/authentication/session/new"
    const val GET_USER_ACCOUNT = "/3/account"

    const val UI_THREAD = "UI_THREAD"
    const val IO_THREAD = "IO_THREAD"

    /*----------------- REQUEST CODE ------------------*/
    const val REQUEST_CODE_CREATE_NEW_LIST = 100
    const val REQUEST_CODE_RATE_MOVIE = 101

    /*----------------- Search type ------------------*/
    const val SEARCH_TYPE_LATEST_MOVIES = 201
    const val SEARCH_TYPE_POPULAR_MOVIES = 202
    const val SEARCH_TYPE_MOVIES_BY_TITLE = 203
    const val SEARCH_TYPE_MOVIES_BY_GENRE = 204
    const val TYPE_FAVORITE_MOVIES = 205
    const val TYPE_WATCHLIST_MOVIES = 206
    const val TYPE_FAVORITE_TV_SHOWS = 207
    const val TYPE_WATCHLIST_TV_SHOWS = 208
    const val SEARCH_TYPE_TVSHOWS = 209
    const val TYPE_RECOMMENDED_MOVIES = 210


    /*----------------- Media type ------------------*/
    const val MEDIA_TYPE_TV = "MEDIA_TYPE_TV"
    const val MEDIA_TYPE_MOVIE = "MEDIA_TYPE_MOVIE"

    /*----------------- KEY CODE ------------------*/
    const val KEY_TITLE = "list_title"
    const val KEY_DESCRIPTION = "list_desc"
    const val KEY_ERROR_CODE = "error_code"

    /*----------------- ERROR CODE ------------------*/
    const val ERROR_CODE_CONNECTION_LOST = -1
    const val ERROR_CODE_UNKNOWN = -2

    enum class MessageType constructor(@param:StringRes @field:StringRes
                                       val messageRes: Int, val isDangerous: Boolean) {
        CONNECTION_PROBLEMS(R.string.err_msg_connection_problem, true),
        USER_NOT_REGISTERED(R.string.err_msg_user_not_registered, true),
        LIST_WAS_DELETED(R.string.err_msg_list_was_deleted, true),
        MOVIE_WAS_DELETED(R.string.err_msg_movie_was_deleted, true),
        TV_SHOW_WAS_DELETED(R.string.err_msg_tv_show_was_deleted, true),
        NEW_LIST_CREATED_SUCCESSFULLY(R.string.msg_new_list_created_successfully, false),
        NEW_MOVIE_ADDED_SUCCESSFULLY(R.string.msg_new_movie_added_successfully, false),
        MOVIE_RATED_SUCCESSFULLY(R.string.msg_movie_rated_successfully, false),
        NEW_MOVIE_REMOVED_SUCCESSFULLY(R.string.msg_new_movie_removed_successfully, true),
        INPUT_MOVIE_TITLE(R.string.error_msg_input_movie_title, true),
        INPUT_STAR_NAME(R.string.error_msg_star_name, true),
        MOVIE_ALREADY_ADDED(R.string.err_msg_movie_added, true),
        UNKNOWN(R.string.err_msg_something_goes_wrong, true)


    }

    enum class PlaceholderType constructor(@param:StringRes @field:StringRes
                                           val messageRes: Int, @param:DrawableRes @field:DrawableRes
                                           val iconRes: Int) {
        NETWORK(R.string.err_msg_connection_problem, R.drawable.ic_cloud_off),
        UNKNOWN(R.string.err_msg_something_goes_wrong, R.drawable.ic_sentiment_dissatisfied),
        EMPTY(R.string.err_msg_no_data, R.drawable.ic_no_data)
    }
}
