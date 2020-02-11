package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinematik.themoviedb.domain.common.DataResult
import com.kinematik.themoviedb.themoviedbappclean.R
import com.kinematik.themoviedb.themoviedbappclean.di.Injectable
import kotlinx.android.synthetic.main.fragment_movies_ongoing.*
import javax.inject.Inject


class OngoingMoviesFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = OngoingMoviesFragment()
    }

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
        adapter.onAddToFavourites = viewModel::onAddToFavourites
        adapter.onShareCallback = viewModel::onShare

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter

        subscribeToViewModel()

        swipe_to_refresh_layout.setOnRefreshListener {
            viewModel.load()
        }


    }

    private fun subscribeToViewModel() {
        viewModel.dataPaged.observe(viewLifecycleOwner, Observer { _result->
            when(_result.status){
                DataResult.Status.SUCCESS ->{
                    _result.data?.let {
                        adapter.submitList(it)

                        swipe_to_refresh_layout.isRefreshing  = false
                    }
                }

                DataResult.Status.LOADING ->{
                    swipe_to_refresh_layout.isRefreshing  = true
                }

                DataResult.Status.ERROR ->{
                    swipe_to_refresh_layout.isRefreshing  = false
                }


            }
        })
    }


}