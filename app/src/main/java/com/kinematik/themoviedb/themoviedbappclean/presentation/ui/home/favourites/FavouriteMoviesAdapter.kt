package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kinematik.themoviedb.themoviedbappclean.R
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.MoviesApiService
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.model.Movie
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.extensions.afterPreDraw
import kotlinx.android.synthetic.main.view_cell_favourites_item.view.*

typealias OnRemoveFromFavouritesCallback = (Movie)->Unit
typealias OnShareCallback = (Movie)->Unit

class FavouriteMoviesAdapter : ListAdapter<Movie, FavouriteMoviesAdapter.ViewHolder>(DiffCallback()) {

    var onRemoveFromFavouritesCallback:OnRemoveFromFavouritesCallback? = null
    var onShareCallback:OnShareCallback? = null

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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.remove_button.setOnClickListener {
                getItem(adapterPosition)?.let {
                    onRemoveFromFavouritesCallback?.invoke(it)
                }
            }

            itemView.share_button.setOnClickListener {
                getItem(adapterPosition)?.let {
                    onShareCallback?.invoke(it)
                }
            }
        }

        fun onBind(item: Movie) {

            itemView.title_text_view.text = item.original_title
            itemView.description_text_view.text = item.overview

            itemView.icon_image_view.afterPreDraw { view, width, height ->
                Glide.with(itemView)
                    .load("${MoviesApiService.ENDPOINT}${item.poster_path}")
                    .apply(
                        RequestOptions()
                            .override(width, height)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.mipmap.ic_launcher)
                    )
                    .into(view)
            }
        }

        fun onUnbind() {
            Glide.with(itemView).clear(itemView.icon_image_view)
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