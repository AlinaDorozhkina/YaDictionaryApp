package ru.alinadorozhkina.yadictionaryapp.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.alinadorozhkina.Contract
import ru.alinadorozhkina.yadictionaryapp.MainInteractor
import ru.alinadorozhkina.yadictionaryapp.api.data.DataSourceRemote
import ru.alinadorozhkina.yadictionaryapp.api.data.RepoImpl

class MainPresenter<T : Contract.AppState, V : Contract.View>(
    private val interactor: MainInteractor = MainInteractor(
        RepoImpl(DataSourceRemote())
    )
) : Contract.Presenter<T, V> {

    private val compositeDisposable = CompositeDisposable()

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (currentView != view) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (currentView == view) {
            currentView = null
        }
    }

    override fun getData(word: String) {
        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { currentView?.renderData(Contract.AppState.Loading(null)) }
                .subscribe ({
                    currentView?.renderData(it)
                }, {
                    currentView?.renderData(Contract.AppState.Error(it))
                })
        )
    }
}