package com.example.hw1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView

class GameOverActivity : AppCompatActivity(){

    private lateinit var gameOver_LBL_status: MaterialTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameover)

        findViews()
        initViews()
    }

    private fun findViews() {
        gameOver_LBL_status = findViewById(R.id.gameOver_LBL_status)
    }

    private fun initViews() {
        val bundle: Bundle? = intent.extras

        val message = bundle?.getString("MESSAGE_KEY","🤷🏻‍♂️ Unknown Status")

        gameOver_LBL_status.text = buildString {
            append(message)
            append("\n")

        }
    }
}