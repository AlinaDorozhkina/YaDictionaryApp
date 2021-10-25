package ru.alinadorozhkina.yadictionaryapp

import io.reactivex.Observable
import ru.alinadorozhkina.Contract
import ru.alinadorozhkina.yadictionaryapp.model.DataModel

class MainInteractor(
    private val repository: Contract.Repository<DataModel>
): Contract.Interactor<Contract.AppState> {
    override fun getData(word: String): Observable<Contract.AppState> {
        return repository.getData(word).map { Contract.AppState.Success(it) }
    }
}