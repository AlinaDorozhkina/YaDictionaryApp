package ru.alinadorozhkina.yadictionaryapp.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alinadorozhkina.yadictionaryapp.model.DataModel

interface ApiService {
    @GET("dicservice.json/lookup?")
    fun search(@Query("key") key: String, @Query("lang") lang: String, @Query("text") text: String): Observable<DataModel>
}