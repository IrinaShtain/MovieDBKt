package ua.shtain.irina.moviedbkt.view.base.content

import ua.shtain.irina.moviedbkt.view.base.IBaseView

/**
 * Created by Irina Shtain on 30.01.2018.
 */
 interface ContentView :IBaseView {
    fun showProgressMain()
    fun showProgressPagination()
    fun hideProgress()
//    fun showMessage(messageType: Constants.MessageType)
//    fun showCustomMessage(msg: String, isDangerous: Boolean)
//    fun showPlaceholder(placeholderType: Constants.PlaceholderType)
}