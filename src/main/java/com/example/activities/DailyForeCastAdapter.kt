package com.example.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/// Item passed to list adapter
/// view Holder to bind the item to layout

class DailyForeCastAdapter(
    // unit is void
    private val clickHandler: (DailyForecast) -> Unit
): ListAdapter<DailyForecast, DailyForecastViewHolder>(DIFF_CONFIG) {

    /// class method
    companion object{
        val DIFF_CONFIG = object :DiffUtil.ItemCallback<DailyForecast>(){
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: DailyForecast,
                newItem: DailyForecast
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        /// create new viewHolder
        // Create new view to repsent each item in list
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast, parent, false)
        return DailyForecastViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        /// each item  is passed to viewHolder
        holder.itemView.setOnClickListener{
            clickHandler(getItem(position))
        }
        holder.bind(getItem(position))
    }
}
/// take view and pass to parent class
class DailyForecastViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val tempText = view.findViewById<TextView>(R.id.tempText)
    private val descriptionText: TextView = view.findViewById(R.id.descriptionText)

    fun bind(dailyForecast: DailyForecast){

        tempText.text = String.format("%.2f",dailyForecast.temp)
        descriptionText.text = dailyForecast.description
    }
}