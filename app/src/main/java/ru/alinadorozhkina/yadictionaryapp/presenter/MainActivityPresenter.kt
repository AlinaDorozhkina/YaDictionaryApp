package ru.alinadorozhkina.yadictionaryapp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.alinadorozhkina.yadictionaryapp.mvp.view.MainActivityView
import ru.alinadorozhkina.yadictionaryapp.navigation.IScreens

class MainActivityPresenter(
    private val router: Router,
    private val screens: IScreens
    ): MvpPresenter<MainActivityView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.toMain())
    }

    fun toFavourites(){

    }

    fun toSettings(){

    }
}