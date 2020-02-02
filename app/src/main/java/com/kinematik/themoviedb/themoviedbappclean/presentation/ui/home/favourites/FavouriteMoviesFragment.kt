package com.kinematik.themoviedb.app.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kinematik.themoviedb.themoviedbappclean.R
import com.kinematik.themoviedb.themoviedbappclean.presentation.di.Injectable


class FavouriteMoviesFragment : Fragment(), Injectable {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_movies_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}