package com.panic1k.rxjava.activities.databasexample.recycler

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.panic1k.rxjava.modellayer.persistencelayer.PhotoDescription
import com.panic1k.rxjava.R
import io.reactivex.disposables.CompositeDisposable

typealias ItemClickedlambda = (v: View, position: Int) -> Unit

class PhotoDescriptionViewAdapter(var onItemClicked: ItemClickedlambda): RecyclerView.Adapter<PhotoDescriptionViewHolder>() {

    internal var photoDescriptions = mutableListOf<PhotoDescription>()
    private val bag = CompositeDisposable()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoDescriptionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo_description, parent, false)
        val viewHolder = PhotoDescriptionViewHolder(view)

        view.setOnClickListener { v -> onItemClicked(v, viewHolder.adapterPosition) }

        return viewHolder
    }

    override fun onBindViewHolder(holder: PhotoDescriptionViewHolder, position: Int) {
        val photoDescription = photoDescriptions[position]
        holder.configureWith(photoDescription)
    }

    override fun getItemCount(): Int = photoDescriptions.size

}