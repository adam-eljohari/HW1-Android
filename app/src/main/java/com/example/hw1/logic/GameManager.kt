package com.example.hw1.logic


class GameManager(private val lifeCount:Int = 3, private val numOfLanes : Int = 3){

    var playerPosition = 1
    var hitPosition = 0
    val obstacleMatrix: Array<Array<Boolean>>

    init {
        obstacleMatrix = getRandomMatrix()
    }

    fun canMovePlayerLeft(): Boolean {
        return playerPosition > 0
    }

    fun canMovePlayerRight(): Boolean {
        return playerPosition < 2
    }

    fun movePlayerLeft() {
        if (canMovePlayerLeft())
            playerPosition--
    }

    fun movePlayerRight() {
        if (canMovePlayerRight())
            playerPosition++
    }

    val isGameOver: Boolean
        get() = hitPosition == lifeCount


    private fun getRandomMatrix() : Array<Array<Boolean>>{
        val arr: Array<Array<Boolean>> = Array(5) { Array(numOfLanes) { false } }
        for(i in 0..3){
            arr[i] = getRandomBooleanArray(numOfLanes)
        }
        return arr
    }

    private fun getRandomBooleanArray(size: Int): Array<Boolean> {
        val array = Array(size) { false }
        val randomIndex = (0 until size).random()
        array[randomIndex] = true
        return array
    }
    fun moveObstaclesDown() {
        for (row in 4 downTo 1) {
            for (col in 0 until 3) {
                obstacleMatrix[row][col] = obstacleMatrix[row - 1][col]
            }
        }
        obstacleMatrix[0] = getRandomBooleanArray(numOfLanes)
    }


    fun checkCollisionObstacle(): Boolean {
        if (obstacleMatrix[4][playerPosition]) {
            hitPosition++
            return true
        }
        return false
    }
}