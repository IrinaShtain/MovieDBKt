package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.adapter

import ua.shtain.irina.moviedbkt.model.lists.ListItem


/**
 * Created by Irina Shtain on 15.02.2018.
 */
class CreatedListsDH constructor(val model: ListItem) {

    fun getListsName() = model.name

    fun getListsID() = model.id

    fun getListsDescription() = if (model.description == "") "No description" else model.description

    fun getListsType() = model.listType
}
