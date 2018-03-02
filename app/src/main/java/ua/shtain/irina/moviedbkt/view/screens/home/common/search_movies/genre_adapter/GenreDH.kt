package ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.genre_adapter

import ua.shtain.irina.moviedbkt.model.genre.GenreItem

/**
 * Created by Irina Shtain on 20.02.2018.
 */
class GenreDH constructor(val model: GenreItem) {

    fun getGenreTitle() = model.name.trim()


    fun getGenreID(): Int {
        return model.id
    }
}