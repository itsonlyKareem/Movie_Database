package network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    public val BASE_URL = "https://api.themoviedb.org/3/"
    public val API_KEY = "7c6461abd639a001e8af7ede73ab1b52"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}