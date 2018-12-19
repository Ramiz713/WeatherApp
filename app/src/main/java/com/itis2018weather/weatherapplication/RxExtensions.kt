package com.itis2018weather.weatherapplication

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.subscribeObservableOnIoObserveOnUi(): Observable<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.subscribeFlowableOnIoObserveOnUi(): Flowable<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.subscribeSingleOnIoObserveOnUi(): Single<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun Completable.subscribeCompletableOnIoObserveOnUi(): Completable =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Maybe<T>.subscribeMaybeOnIoObserveOnUi(): Maybe<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
