package ru.mamykin.foboreader.core.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mamykin.foboreader.core.extension.toLiveData
import ru.mamykin.foboreader.core.platform.Schedulers
import rx.Single
import rx.Subscription
import rx.subscriptions.CompositeSubscription

abstract class BaseViewModel : ViewModel() {

    protected abstract val schedulers: Schedulers
    private val compositeSubscription = CompositeSubscription()
    val _error = MutableLiveData<String>()
    val error = _error.toLiveData()

    protected fun Subscription.unsubscribeOnDestroy() {
        compositeSubscription.add(this)
    }

    protected fun <T> Single<T>.applySchedulers(): Single<T> =
            subscribeOn(schedulers.io())
                    .observeOn(schedulers.main())
}