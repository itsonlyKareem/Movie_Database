package network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") key: String,
        @Query("language") language: String
    ): Call<ResponseBody>

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") key: String,
        @Query("page") page: Int,
        @Query("sort_by") filter: String,
        @Query("with_genres") genreId: Int
    ): Call<ResponseBody>

    @GET("discover/movie")
    fun getAllMovies(
        @Query("api_key") key: String,
        @Query("page") page:Int
    ): Call<ResponseBody>
}