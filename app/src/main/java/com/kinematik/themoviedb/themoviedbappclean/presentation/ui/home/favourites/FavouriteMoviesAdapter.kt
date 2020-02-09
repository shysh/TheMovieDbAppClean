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
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.model.MoviePresentationDao
import com.kinematik.themoviedb.themoviedbappclean.presentation.ui.extensions.afterPreDraw
import kotlinx.android.synthetic.main.view_cell_favourites_item.view.*

typealias OnRemoveFromFavouritesCallback = (MoviePresentationDao)->Unit
typealias OnShareCallback = (MoviePresentationDao)->Unit

class FavouriteMoviesAdapter : ListAdapter<MoviePresentationDao, FavouriteMoviesAdapter.ViewHolder>(DiffCallback()) {

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

        fun onBind(item: MoviePresentationDao) {

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

    private class DiffCallback : DiffUtil.ItemCallback<MoviePresentationDao>() {

        override fun areItemsTheSame(oldItem: MoviePresentationDao, newItem: MoviePresentationDao): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviePresentationDao, newItem: MoviePresentationDao): Boolean {
            return oldItem == newItem
        }
    }
}