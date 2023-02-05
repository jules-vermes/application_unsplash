package com.example.julobox

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.appjulo.ImageDetailsActivity
import com.example.appjulo.R
import com.example.appjulo.UnsplashPhoto
import com.squareup.picasso.Picasso

class UnsplashAdapter(
    private val context: Context,
    private var photoList: List<UnsplashPhoto>,
    private val listener: OnImageClickListener
) : RecyclerView.Adapter<UnsplashAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photoList[position]
        Picasso.get().load(photo.urls.regular).into(holder.imageView)
        holder.itemView.setOnClickListener {
            listener.onImageClick(position)
        }
    }

    override fun getItemCount(): Int = photoList.size
}

interface OnImageClickListener {
    fun onImageClick(position: Int)
}
