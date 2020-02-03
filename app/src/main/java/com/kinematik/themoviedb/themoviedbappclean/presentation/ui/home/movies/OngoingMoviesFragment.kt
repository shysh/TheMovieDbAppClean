package com.kinematik.themoviedb.app.view.home

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
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies.OngoingMoviesAdapter
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies.OngoingMoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies_ongoing.*
import javax.inject.Inject


class OngoingMoviesFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: OngoingMoviesViewModel by viewModels {
        viewModelFactory
    }

    lateinit var adapter: OngoingMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_movies_ongoing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = OngoingMoviesAdapter()

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter


    }


}