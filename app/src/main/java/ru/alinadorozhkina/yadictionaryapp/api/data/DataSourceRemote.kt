package ru.alinadorozhkina.yadictionaryapp.api.data

import io.reactivex.Observable
import ru.alinadorozhkina.Contract
import ru.alinadorozhkina.yadictionaryapp.api.RetrofitImpl
import ru.alinadorozhkina.yadictionaryapp.model.DataModel

class DataSourceRemote(
    private val remoteProvider: RetrofitImpl = RetrofitImpl()
): Contract.DataSource<DataModel> {
    override fun getData(word: String): Observable<DataModel> {
        return remoteProvider.getData(word)
    }
}