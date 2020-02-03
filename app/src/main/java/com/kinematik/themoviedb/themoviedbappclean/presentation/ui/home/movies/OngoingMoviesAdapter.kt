package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kinematik.themoviedb.themoviedbappclean.R
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.model.Movie

class OngoingMoviesAdapter : ListAdapter<Movie, OngoingMoviesAdapter.ViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_cell_movies_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { _item ->
            holder.onBind(_item)
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.onUnbind()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun onBind(item: Movie) {

        }

        fun onUnbind() {

        }

    }

    private class DiffCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}