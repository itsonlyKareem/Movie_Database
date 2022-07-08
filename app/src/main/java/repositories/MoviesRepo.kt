package repositories

import androidx.lifecycle.MutableLiveData
import network.ApiClient
import network.ApiService
import network.models.Genre
import network.models.Movie
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepo() {
    val genresLiveData = MutableLiveData<MutableList<Genre>>()
    val moviesLiveData = MutableLiveData<MutableList<Movie>>()


    fun getGenres() {
        val tempList: MutableList<Genre> = arrayListOf()
        val apiService: ApiService = ApiClient.getInstance().create(ApiService::class.java)
        val result = apiService.getGenres("7c6461abd639a001e8af7ede73ab1b52", "en-US")
        result.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {

                    val jsonObject = JSONObject(response.body()!!.string())
                    val genres = jsonObject.getJSONArray("genres")

                    for (i in 0 until genres.length()) {
                        var id = genres.getJSONObject(i).getInt("id")
                        var name = genres.getJSONObject(i).getString("name")
                        tempList.add(Genre(id, name))
                    }


                    genresLiveData.postValue(tempList)

                } else {
                    println("Response Error")
                    println(response.code())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            }

        })
    }

    fun getMovies(genreId: Int, page: Int) {
        var tempList: MutableList<Movie> = arrayListOf()
        val apiService: ApiService = ApiClient.getInstance().create(ApiService::class.java)

        if (genreId!=0) {
            val result = apiService.getMovies(
                "7c6461abd639a001e8af7ede73ab1b52",
                page,
                "popularity.desc",
                genreId
            )
            result.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        val jsonObject = JSONObject(response.body()!!.string())
                        val movies = jsonObject.getJSONArray("results")

                        for (i in 0 until movies.length()) {
                            val movieObject = movies.getJSONObject(i)

                            val adult = movieObject.getBoolean("adult")
                            val id = movieObject.getInt("id")
                            val image = movieObject.getString("poster_path")
                            val title = movieObject.getString("title")
                            val rating = movieObject.getDouble("vote_average")

                            tempList.add(Movie(adult, id, image, rating, title))
                        }
                    } else {
                        println("Response Error")
                    }

                    moviesLiveData.postValue(tempList)
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }

            })
        } else {
            val result = apiService.getAllMovies(
                "7c6461abd639a001e8af7ede73ab1b52",
                page
            )
            result.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        val jsonObject = JSONObject(response.body()!!.string())
                        val movies = jsonObject.getJSONArray("results")

                        for (i in 0 until movies.length()) {
                            val movieObject = movies.getJSONObject(i)

                            val adult = movieObject.getBoolean("adult")
                            val id = movieObject.getInt("id")
                            val image = movieObject.getString("poster_path")
                            val title = movieObject.getString("title")
                            val rating = movieObject.getDouble("vote_average")

                            tempList.add(Movie(adult, id, image, rating, title))
                        }
                    } else {
                        println("Response Error")
                    }

                    moviesLiveData.postValue(tempList)
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }

            })
        }

    }
}