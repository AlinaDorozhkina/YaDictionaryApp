package ru.alinadorozhkina.yadictionaryapp.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.alinadorozhkina.Contract
import ru.alinadorozhkina.yadictionaryapp.R
import ru.alinadorozhkina.yadictionaryapp.databinding.FragmentMainBinding
import ru.alinadorozhkina.yadictionaryapp.presenter.MainPresenter
import ru.alinadorozhkina.yadictionaryapp.ui.adapter.MainAdapter
import ru.alinadorozhkina.yadictionaryapp.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding, Contract.AppState>() {

    override var bindingNullable: FragmentMainBinding? = null
    private var adapter: MainAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMainBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun createPresenter(): Contract.Presenter<Contract.AppState, Contract.View> {
        return MainPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputLayout.editText?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                binding.inputLayout.setEndIconOnClickListener {
                    presenter.getData(s.toString())
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })
    }

    override fun renderData(appState: Contract.AppState) {
        when (appState) {
            is Contract.AppState.Success -> {
                val dataModel = appState.data
                showViewSuccess()
                if (adapter == null) {
                    binding.mainActivityRecyclerview.layoutManager =
                        LinearLayoutManager(requireContext())
                    adapter = MainAdapter(dataModel)
                    binding.mainActivityRecyclerview.adapter =
                        adapter


                } else {
                    adapter!!.setData(dataModel)
                }
            }
            is Contract.AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is Contract.AppState.Error -> {
                showErrorScreen(appState.t.message)
                Log.d("tag", appState.t.message.toString())
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            presenter.getData("hi")
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = View.VISIBLE
        binding.loadingFrameLayout.visibility = View.GONE
        binding.errorLinearLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = View.GONE
        binding.loadingFrameLayout.visibility = View.VISIBLE
        binding.errorLinearLayout.visibility = View.GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = View.GONE
        binding.loadingFrameLayout.visibility = View.GONE
        binding.errorLinearLayout.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = MainFragment()
    }



}