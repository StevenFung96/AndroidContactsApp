package com.example.hexa_stevenfung.androidcontactsapp.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * extension method to inflate a new view using LayoutInflater with the minimum effort
 */

fun ViewGroup.inflate(layoutRes: Int): View{
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

/**
 * extension method to load any image url into the image view object using Glide with the minimum effort
 */

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}