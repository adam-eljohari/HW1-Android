package com.example.hw1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.hw1.logic.GameManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {


    private lateinit var main_IMG_spaceship: ImageView
    private lateinit var main_IMG_hearts: Array<AppCompatImageView>
    private lateinit var main_FAB_right: ExtendedFloatingActionButton
    private lateinit var main_FAB_left: ExtendedFloatingActionButton
    private lateinit var main_IMG_players: Array<AppCompatImageView>
    private lateinit var main_IMG_obstacles: Array<Array<AppCompatImageView>>
    private lateinit var gameManager: GameManager




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
        gameManager = GameManager(main_IMG_hearts.size)
        initViews()

    }
    private fun initViews() {

    }



    @SuppressLint("UnsafeIntentLaunch")
    private fun changeActivity() {
        val bundle = Bundle()
        bundle.putString("MESSAGE_KEY", "😭Game Over! ")
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }


    private fun findViews() {

    }





}