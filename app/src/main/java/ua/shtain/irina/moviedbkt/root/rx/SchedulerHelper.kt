package ua.shtain.irina.moviedbkt.root.rx

import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by Irina Shtain on 12.02.2018.
 */
 class SchedulerHelper @Inject constructor(observeOn: Scheduler,
                                           subscribeOn: Scheduler) {
    val mObserveOn = observeOn
    val mSubscribeOn = subscribeOn

    fun <T> getNetworkObservable(observable: Observable<T>): Observable<T> {
        return observable.observeOn(mObserveOn)
                .subscribeOn(mSubscribeOn)
    }

}
