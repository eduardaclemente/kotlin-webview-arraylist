package com.aula.httpappb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var textName = findViewById<TextView>(R.id.textName)
        val name = intent.getStringExtra("item-selecionado")
        textName.setText(name)
    }
}