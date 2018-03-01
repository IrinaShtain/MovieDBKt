package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.adapter

import ua.shtain.irina.moviedbkt.model.lists.ListItem


/**
 * Created by Irina Shtain on 15.02.2018.
 */
class CreatedListsDH constructor(val model: ListItem) {

    val listName : String
        get() = model.name

    val id :Int
        get() = model.id

    val description : String
        get() = if (model.description == "") "No description" else model.description

    val type : String
        get() = model.listType
}
