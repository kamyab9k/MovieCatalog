package menuFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieCatalog.MovieAdapter

import com.example.movieCatalog.R
import com.example.movieCatalog.databinding.FragmentHomeBinding
import com.example.movieCatalog.services.MovieViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MovieViewModel
    private var movieAdapter: MovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        movieAdapter = MovieAdapter(requireActivity())
        setupMovieRecyclerView()
        getMovieData()
        observeMovieData()
    }

    private fun observeMovieData() {
        viewModel.movieListLiveData.observe(requireActivity()) { movies ->
            movieAdapter?.updateMovies(movies)
        }
    }

    private fun getMovieData() {
        viewModel.fetchMovies()
    }

    private fun setupMovieRecyclerView() {
        binding.rvMoviesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}
