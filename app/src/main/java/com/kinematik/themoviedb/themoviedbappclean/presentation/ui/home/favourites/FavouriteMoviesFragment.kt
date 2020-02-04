package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinematik.themoviedb.themoviedbappclean.R
import com.kinematik.themoviedb.themoviedbappclean.presentation.di.Injectable
import kotlinx.android.synthetic.main.fragment_movies_favourites.*
import javax.inject.Inject


class FavouriteMoviesFragment : Fragment(), Injectable {

    companion object{
        fun newInstance() = FavouriteMoviesFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: FavouriteMoviesViewModel by viewModels {
        viewModelFactory
    }

    lateinit var adapter: FavouriteMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_movies_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavouriteMoviesAdapter()
        adapter.onRemoveFromFavouritesCallback = viewModel::onRemoveFromFavourites
        adapter.onShareCallback = viewModel::onShare

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter

    }


}