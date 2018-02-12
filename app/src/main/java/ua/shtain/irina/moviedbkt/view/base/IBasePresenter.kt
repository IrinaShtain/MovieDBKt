package ua.shtain.irina.moviedbkt.view.base


/**
 * Created by Irina Shtain on 30.01.2018.
 */
interface IBasePresenter< T : IBaseView>  {
    fun setView(view:T)
    fun subscribe()
    fun unsubscribe()
}
