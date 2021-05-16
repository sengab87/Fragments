package com.example.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


// activities define layout which has layout container and views ///
// Recycle view layoutManager // Adapter connects data to individual views
// viewHolder bind data

/// Repo load or form data either from internet or device /// view Model
/// repo data to activity liveData ///
/// Live Data observable data Holder -----> observe live data from activity which will change when
// Repo update live data.   Repo is Like data source /// Adapter like delegate //

// Live data must be observed /// MutableLiveData
class MainActivity : AppCompatActivity() {

    /// Observer to know when it updated
    private val forecastRespository = ForecastRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val zipCodeEditText: EditText = findViewById(R.id.zipcodeEditText)
        val enterButton: Button = findViewById(R.id.enterButton)
        enterButton.setOnClickListener{
            val zipCode : String = zipCodeEditText.text.toString()
            if (zipCode.length != 5){
                Toast.makeText(this,"error", Toast.LENGTH_SHORT).show()

            }else {

                forecastRespository.loadForecast(zipCode)
            }
        }

        val forecastList: RecyclerView = findViewById(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(this)
        val dailyForeCastAdapter = DailyForeCastAdapter(){
            /// clicked click handler
            val msg = getString(R.string.forecast_clicked_format,it.temp, it.description)
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        }
        forecastList.adapter = dailyForeCastAdapter
        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->

            /// UPDATE OUT LIST ADAPTER
            dailyForeCastAdapter.submitList(forecastItems)

        }

        forecastRespository.weeklyForecast.observe(this, weeklyForecastObserver)


    }
}
