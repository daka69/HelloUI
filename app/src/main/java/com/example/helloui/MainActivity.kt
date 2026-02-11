package com.example.helloui

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var etName: TextInputEditText
    private lateinit var switchTheme: MaterialSwitch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        etName = findViewById(R.id.etName)
        switchTheme = findViewById(R.id.switchTheme)
        val btnSapa = findViewById<Button>(R.id.btnSapa)

        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val isDarkMode = currentNightMode == Configuration.UI_MODE_NIGHT_YES

        switchTheme.isChecked = isDarkMode
        updateSwitchText(isDarkMode)

        if (savedInstanceState != null) {
            val savedText = savedInstanceState.getString("KEY_HASIL_SAPA")
            tvResult.text = savedText
        }

        btnSapa.setOnClickListener {
            val nama = etName.text.toString()
            if (nama.isNotEmpty()) {
                tvResult.text = "Hello $nama!"
            } else {
                tvResult.text = "Hello Siapa Kamu?"
            }
        }

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            updateSwitchText(isChecked)

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("KEY_HASIL_SAPA", tvResult.text.toString())
    }

    private fun updateSwitchText(isDark: Boolean) {
        if (isDark) {
            switchTheme.text = "Mode Gelap"
        } else {
            switchTheme.text = "Mode Terang"
        }
    }
}