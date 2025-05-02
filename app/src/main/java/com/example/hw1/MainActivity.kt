package com.example.hw1


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.hw1.logic.GameManager
import com.example.hw1.utilities.Constants
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


class MainActivity : AppCompatActivity() {


    private lateinit var main_IMG_hearts: Array<AppCompatImageView>
    private lateinit var main_FAB_right: ExtendedFloatingActionButton
    private lateinit var main_FAB_left: ExtendedFloatingActionButton
    private lateinit var main_IMG_players: Array<AppCompatImageView>
    private lateinit var main_IMG_obstacles: Array<Array<AppCompatImageView>>
    private lateinit var gameManager: GameManager

    val handler = Handler(Looper.getMainLooper())


    private val runnable = object : Runnable {
        override fun run() {
            gameManager.moveObstaclesDown()
            refreshUI()
            handler.postDelayed(this, Constants.GameLogic.DELAY_MILLIS)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
        gameManager = GameManager(main_IMG_hearts.size)
        initViews()

    }
    private fun initViews() {
        main_FAB_right.setOnClickListener {
            movePlayerRight()
        }
        main_FAB_left.setOnClickListener {
            movePlayerLeft()
        }
        refreshUI()
        handler.postDelayed(runnable, Constants.GameLogic.DELAY_MILLIS)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }
    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }

    private fun findViews() {

        main_IMG_hearts = arrayOf(
            findViewById(R.id.main_IMG_heart0),
            findViewById(R.id.main_IMG_heart1),
            findViewById(R.id.main_IMG_heart2)
        )

        main_FAB_right = findViewById(R.id.main_FAB_right)
        main_FAB_left = findViewById(R.id.main_FAB_left)

        main_IMG_players = arrayOf(
            findViewById(R.id.main_IMG_player1),
            findViewById(R.id.main_IMG_player2),
            findViewById(R.id.main_IMG_player3)
        )

        main_IMG_obstacles = arrayOf(
                arrayOf(
                    findViewById(R.id.main_IMG_obstacle1),
                     findViewById(R.id.main_IMG_obstacle2),
                    findViewById(R.id.main_IMG_obstacle3)
                ),
                arrayOf(
                    findViewById(R.id.main_IMG_obstacle4),
                    findViewById(R.id.main_IMG_obstacle5),
                    findViewById(R.id.main_IMG_obstacle6)
                ),
                arrayOf(
                    findViewById(R.id.main_IMG_obstacle7),
                    findViewById(R.id.main_IMG_obstacle8),
                    findViewById(R.id.main_IMG_obstacle9)
                ),
                arrayOf(
                    findViewById(R.id.main_IMG_obstacle10),
                    findViewById(R.id.main_IMG_obstacle11),
                    findViewById(R.id.main_IMG_obstacle12)
                ),
                arrayOf(
                    findViewById(R.id.main_IMG_obstacle13),
                    findViewById(R.id.main_IMG_obstacle14),
                    findViewById(R.id.main_IMG_obstacle15)
                )
        )
    }
    private fun movePlayerLeft() {
        if (gameManager.canMovePlayerLeft()) {
            gameManager.movePlayerLeft()
            refreshUI()
        }
    }

    private fun movePlayerRight() {
        if (gameManager.canMovePlayerRight()) {
            gameManager.movePlayerRight()
            refreshUI()
        }
    }
    private fun showMessage(txt: String) {
        Toast.makeText(applicationContext,txt,Toast.LENGTH_SHORT).show()
        vibrateOnce(this)
    }
    private fun vibrateOnce(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            val effect = VibrationEffect.createOneShot(Constants.GameLogic.DURATION, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(effect)
        }
    }
    private fun refreshUI() {
        if (gameManager.isGameOver) {
            showMessage("Game Over! 😭 ")
            changeActivity("Game Over! 😭 ")
        } else {
            updatePlayers()
            updateObstacles()

            if (gameManager.checkCollisionObstacle()) {
               showMessage("You hit the moon , it's not good!")

            }
            updateHearts()
        }
    }
    private fun updatePlayers() {
        for (i in main_IMG_players.indices) {
            if (i == gameManager.playerPosition) {
                main_IMG_players[i].visibility = View.VISIBLE
            } else {
                main_IMG_players[i].visibility = View.INVISIBLE
            }
        }
    }

    private fun updateObstacles() {
        for (row in main_IMG_obstacles.indices) {
            for (col in main_IMG_obstacles[row].indices) {
                if (gameManager.obstacleMatrix[row][col]) {
                    main_IMG_obstacles[row][col].visibility = View.VISIBLE
                } else {
                    main_IMG_obstacles[row][col].visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun updateHearts() {
        if (gameManager.hitPosition != 0 && gameManager.hitPosition <= main_IMG_hearts.size) {
            main_IMG_hearts[main_IMG_hearts.size - gameManager.hitPosition].visibility =
                View.INVISIBLE
        }
    }


    private fun changeActivity(message: String) {
        handler.removeCallbacks(runnable)
        val intent = Intent(this, GameOverActivity::class.java)
        val bundle = Bundle()
        bundle.putString(Constants.BundleKeys.STATUS_KEY, message)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
        println("here")

    }




}