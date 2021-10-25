package ru.alinadorozhkina

import io.reactivex.Observable
import ru.alinadorozhkina.yadictionaryapp.model.DataModel

class Contract {
    sealed interface AppState {
        data class Success(val data: DataModel) : AppState
        data class Error(val t: Throwable) : AppState
        data class Loading(val progress: Int? = null) : AppState
    }

    interface View {
        fun renderData(appState: AppState)
    }

    interface Presenter<T : AppState, V : View> {
        fun attachView(view: V)

        fun detachView(view: V)

        fun getData(word: String)
    }

    // бизнес-логика
    interface Interactor<T> {
        fun getData(word: String): Observable<T>
    }

    // Получение и/или хранение данных для передачи интерактору
    interface Repository<T> {
        fun getData(word: String): Observable<T>
    }

    // Источник данных для репозитория
    interface DataSource<T> {
        fun getData(word: String): Observable<T>
    }
}