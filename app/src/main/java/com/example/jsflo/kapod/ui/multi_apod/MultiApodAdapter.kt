package com.example.jsflo.kapod.ui.multi_apod

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jsflo.kapod.R
import com.example.jsflo.kapod.entity.Apod
import com.example.jsflo.kapod.utils.loadImg
import com.example.jsflo.kapod.utils.toPrettyFormat
import kotlinx.android.synthetic.main.single_apod_activity.view.*
import kotlinx.android.synthetic.main.view_apod_text.view.*

class MultiApodAdapter(val listOfApod: MutableList<Apod> = mutableListOf<Apod>(), val listener: MultiApodListener? = null)
    : RecyclerView.Adapter<ApodViewHolder>(), ApodViewHolder.ApodViewHolderListener {
    interface MultiApodListener {
        fun onApod(apod: Apod)
    }

    override fun getItemCount(): Int = listOfApod.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ApodViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_apod, parent, false)
        return ApodViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: ApodViewHolder?, position: Int) {
        holder?.onBindViewHolder(listOfApod[position])
    }

    override fun onApodClick(adapterPosition: Int) {
        listener?.onApod(listOfApod[adapterPosition])
    }

    fun swapItems(apods: List<Apod>) {
        listOfApod.clear()
        listOfApod.addAll(apods)
        notifyDataSetChanged()
    }
}

class ApodViewHolder(itemView: View, val listener: ApodViewHolderListener? = null) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    interface ApodViewHolderListener {
        fun onApodClick(adapterPosition: Int)
    }

    fun onBindViewHolder(apod: Apod) {
        itemView.hero_image.loadImg(apod.url)
        itemView.apod_title.text = apod.title
        itemView.apod_date.text = apod.date.toPrettyFormat()
    }

    override fun onClick(v: View?) {
        listener?.onApodClick(adapterPosition)
    }
}