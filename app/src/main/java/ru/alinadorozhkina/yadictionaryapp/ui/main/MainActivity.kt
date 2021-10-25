package ru.alinadorozhkina.yadictionaryapp.ui.main

import android.os.Bundle
import ru.alinadorozhkina.yadictionaryapp.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.alinadorozhkina.yadictionaryapp.app.App
import ru.alinadorozhkina.yadictionaryapp.mvp.view.MainActivityView
import ru.alinadorozhkina.yadictionaryapp.R
import ru.alinadorozhkina.yadictionaryapp.presenter.MainActivityPresenter
import ru.alinadorozhkina.yadictionaryapp.ui.navigation.AndroidScreens


class MainActivity : MvpAppCompatActivity(), MainActivityView {
    val navigator = AppNavigator(this, R.id.container)

    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter { MainActivityPresenter(App.instance.router, AndroidScreens()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }
}