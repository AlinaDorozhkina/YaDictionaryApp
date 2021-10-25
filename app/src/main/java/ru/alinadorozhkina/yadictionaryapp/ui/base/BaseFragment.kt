package ru.alinadorozhkina.yadictionaryapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.alinadorozhkina.Contract

abstract class BaseFragment<VB: ViewBinding, T: Contract.AppState>: Fragment(), Contract.View {
    protected abstract var bindingNullable: VB?
    protected val binding get() = bindingNullable!!


    protected lateinit var presenter: Contract.Presenter<T, Contract.View>

    protected abstract fun createPresenter(): Contract.Presenter<T, Contract.View>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = createPresenter()
    }


    abstract override fun renderData(appState: Contract.AppState)

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
    }
}