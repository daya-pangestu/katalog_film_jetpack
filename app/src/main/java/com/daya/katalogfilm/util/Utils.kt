package com.daya.katalogfilm.util

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.request.RequestOptions
import com.daya.katalogfilm.R
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.viewmodel.factory.ViewModelFactory

const val BASE_URL = "https://image.tmdb.org/t/p/w342"

fun ImageView.loadWithGLide(context: Context, url: String, placeholder :Int = R.drawable.ic_loading, error : Int = R.drawable.ic_error_loading_image) {
    GlideApp.with(context)
        .load(BASE_URL + url)
        .placeholder(placeholder)
        .error(error)
        .apply(RequestOptions()
            .centerCrop())
        .into(this)
}


fun <T : ViewModel> obtainViewModel(activity: FragmentActivity, viewModel :Class<T> ) :T {
    val factory = ViewModelFactory.getInstance(activity.application)

    return ViewModelProviders.of(activity, factory).get(viewModel)
}
