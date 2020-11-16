package com.example.oketestapp.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.oketestapp.R
import com.squareup.picasso.Picasso

object BindingAdapters{
    @BindingAdapter("android:src")
    @JvmStatic fun setImageSource(imageView: ImageView, source: String){
        if(source.isEmpty())
            Picasso.get().load(R.drawable.ic_error).into(imageView)
        else
            Picasso.get().load(source).into(imageView)
    }
}