package br.com.madebygallo.vinapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.madebygallo.vinapp.R
import br.com.madebygallo.vinapp.model.Wine
import coil.api.load
import kotlinx.android.synthetic.main.wine_items_layout.view.*

/**
 * Created by RaqG on 27/07/2020.
 */

class WineAdapter(private val context: Context) : RecyclerView.Adapter<WineAdapter.ViewHolder>() {

    private var mData: MutableList<Wine> = arrayListOf()

    fun addItems(items: MutableList<Wine>) {
        if (!items.isNullOrEmpty()) {
            mData.clear()
            mData.addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.wine_items_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        holder.itemView.imgWine.load(item.wineImage)
        holder.itemView.tvName.text = item.wineName
        holder.itemView.tvProducer.text = item.producer
        holder.itemView.tvVintage.text = item.vintage.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}