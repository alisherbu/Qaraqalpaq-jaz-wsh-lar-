package com.example.qaraqalpaqjazwshlar.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qaraqalpaqjazwshlar.R
import kotlinx.android.synthetic.main.avtivity_bio.*

class BioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avtivity_bio)
        val bio = intent.getStringExtra("bio")
        tvBio.text = bio
    }
}