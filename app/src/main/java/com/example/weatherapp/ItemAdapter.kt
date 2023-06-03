package com.example.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.network.NthDay

class ItemAdapter(private val context: Context,private val data: List<NthDay>?) : RecyclerView.Adapter<ItemAdapter.ForecastViewHolder>() {
    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayTV: TextView = itemView.findViewById(R.id.day)
        val avgTmpTV: TextView = itemView.findViewById(R.id.avgTmp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return ForecastViewHolder(adapterLayout)
    }

    override fun getItemCount() = data!!.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        when(position) {
            0 -> holder.dayTV.text = context.resources.getString(R.string.today)
            1 -> holder.dayTV.text = context.resources.getString(R.string.tomorrow)
            else -> holder.dayTV.text = context.resources.getString(R.string.overmorrow)
        }
        if(data!=null)
            holder.avgTmpTV.text = context.resources.getString(R.string.avg_tmp , data[position].day?.averageTmp)
    }
}