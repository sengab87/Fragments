package com.example.activities

import android.content.Context

enum class TempDisplaySetting {
    Farenheit, Celsius
}
class TempDisplaySettingManager(context: Context) {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun updateSetting(setting: TempDisplaySetting){
        preferences.edit().putString("key_temp_display",setting.name).commit()
    }

    fun getTempDisplaySetting(): TempDisplaySetting {
        val settingValue = preferences.getString("key_temp_display", TempDisplaySetting.Farenheit.name) ?: TempDisplaySetting.Farenheit.name
        return TempDisplaySetting.valueOf(settingValue)
    }
}