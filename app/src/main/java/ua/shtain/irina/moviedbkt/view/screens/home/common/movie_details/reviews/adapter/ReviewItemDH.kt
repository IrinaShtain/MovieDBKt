package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.reviews.adapter

import ua.shtain.irina.moviedbkt.model.movie.review.ReviewItem

/**
 * Created by Alex Shtain on 20.03.2018.
 */
class ReviewItemDH(val model: ReviewItem) {
    val author: String
        get() = model.author
    val content: String
        get() = model.content
}