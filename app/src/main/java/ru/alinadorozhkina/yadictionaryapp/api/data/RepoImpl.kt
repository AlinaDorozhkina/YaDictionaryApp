package ru.alinadorozhkina.yadictionaryapp.api.data

import io.reactivex.Observable
import ru.alinadorozhkina.Contract
import ru.alinadorozhkina.yadictionaryapp.model.DataModel

class RepoImpl(
    private val dataSource: Contract.DataSource<DataModel>
) : Contract.Repository<DataModel> {
    override fun getData(word: String): Observable<DataModel> {
        return dataSource.getData(word)
    }
}