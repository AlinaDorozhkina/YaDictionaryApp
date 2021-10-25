package ru.alinadorozhkina.yadictionaryapp.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.alinadorozhkina.Contract
import ru.alinadorozhkina.yadictionaryapp.model.DataModel

class RetrofitImpl: Contract.DataSource<DataModel> {
    override fun getData(word: String): Observable<DataModel> {
        return createRetrofit().create<ApiService>().search(API_KEY, "en-ru", word)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    companion object {
        private const val BASE_URL_LOCATIONS = "https://dictionary.yandex.net/api/v1/"
        private const val API_KEY = "dict.1.1.20211023T160202Z.82883889c61e3521.6d4a16a2787f196e98639fb90e058be9fe7de230"
    }
}