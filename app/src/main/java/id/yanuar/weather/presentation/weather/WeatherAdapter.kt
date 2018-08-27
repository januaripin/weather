package id.yanuar.weather.presentation.weather

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import id.yanuar.weather.R
import id.yanuar.weather.data.remote.response.ForecastDay
import id.yanuar.weather.util.DateUtils
import kotlinx.android.synthetic.main.item_forecast.view.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    var items: MutableList<ForecastDay> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder =
            WeatherHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false))

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    class WeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ForecastDay) = with(itemView) {
            Picasso.get()
                    .load("https:${item.day.condition.icon}")
                    .into(imageCondition)
            tvDay.text = DateUtils.getDay(item.date)
            tvTemp.text = "${item.day.avgTemp}°"
        }
    }
}