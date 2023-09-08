
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieVieModel: ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // for Handle failure or error here if needed
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _movies.postValue(response.body()?.movies)
            }
        })
    }
}