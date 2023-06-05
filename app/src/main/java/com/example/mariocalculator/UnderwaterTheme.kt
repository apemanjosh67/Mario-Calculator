package com.example.mariocalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class UnderwaterTheme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_underwater_theme)

        //overworld
        var owButton = findViewById<android.widget.Button>(R.id.overworld);
        owButton.setOnClickListener{
            val intent = Intent(this, OverworldTheme::class.java);
            startActivity(intent);
        };
        //underground
        var ugButton = findViewById<android.widget.Button>(R.id.underground);
        ugButton.setOnClickListener{
            val intent = Intent(this, UndergroundTheme::class.java);
            startActivity(intent);
        };
        //castle
        var caButton = findViewById<android.widget.Button>(R.id.castle);
        ugButton.setOnClickListener{
            val intent = Intent(this, CastleTheme::class.java);
            startActivity(intent);
        };
        //underwater
        var uwButton = findViewById<android.widget.Button>(R.id.underwater);
        ugButton.setOnClickListener{
            val intent = Intent(this, UnderwaterTheme::class.java);
            startActivity(intent);
        };
    }
}