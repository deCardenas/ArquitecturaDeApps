package com.albireo.myapplication.views

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.albireo.myapplication.R
import com.albireo.myapplication.service.data.MovieItem
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieItemAdapter(private var movieList: ArrayList<MovieItem>) : RecyclerView.Adapter<MovieItemAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, typeLayout: Int): MovieViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie_list, viewGroup,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        viewHolder.bind(movieList[position])
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movieItem: MovieItem) {
            itemView.txtMovieTitle.text = movieItem.name
            itemView.txtMovieCountry.text = movieItem.country
        }
    }
}