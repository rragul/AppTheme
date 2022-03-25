package com.ragul.android.apptheme

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate

lateinit var shared : SharedPreferences

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shared = getSharedPreferences("change" , MODE_PRIVATE)

        val switch : Switch = findViewById(R.id.dSwitch)

        switch.isChecked = update("checked")
        if (switch.isChecked){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        switch.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked){
                saveIntoSharedPrefs("checked", true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else{
                saveIntoSharedPrefs("checked", false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

    }
    private fun saveIntoSharedPrefs(key: String, value: Boolean){
        val edit = shared.edit()
        edit.putBoolean(key,value)
        edit.commit()
    }

    private fun update(key: String): Boolean{
        return shared.getBoolean(key, false)
    }

}