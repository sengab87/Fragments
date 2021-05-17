package com.example.activities.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.activities.R
import com.example.activities.TempDisplaySetting
import com.example.activities.TempDisplaySettingManager
import com.example.activities.formatTempForDisplay

class ForecastDetailsActivity : AppCompatActivity() {
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)
        tempDisplaySettingManager = TempDisplaySettingManager(this)
        setTitle(R.string.forecast_details)

        val tempText: TextView = findViewById(R.id.tempText)
        val tempDescription: TextView = findViewById(R.id.descriptionText)

        val temp = intent.getFloatExtra("key_temp",0f)
        val description = intent.getStringExtra("key_description")

        tempText.text = formatTempForDisplay(temp, tempDisplaySettingManager.getTempDisplaySetting())
        tempDescription.text = description
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.tempDisplaySetttings -> {
                showTempDisplaySettingDialog()
                return true
            } else -> return super.onOptionsItemSelected(item)

        }
    }
    private fun showTempDisplaySettingDialog(){
        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle("Choose Display Units")
            .setMessage("Choose which temperature unit to use for temperature display")
            .setPositiveButton("F°"){_, _ ->
                tempDisplaySettingManager.updateSetting(TempDisplaySetting.Farenheit)
            }
            .setNeutralButton("C°") {_, _ ->
                tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celsius)
            }
            .setOnDismissListener(){
                Toast.makeText(this,"Dismiised",Toast.LENGTH_SHORT).show()
            }

        dialogBuilder.show()

    }
}
