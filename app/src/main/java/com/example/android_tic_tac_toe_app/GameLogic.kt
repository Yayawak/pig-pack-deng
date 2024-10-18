package com.example.android_tic_tac_toe_app

import kotlin.math.abs

class GameLogic(private val boardSize: Int, private val playerXName: String, private val playerOName: String) {
    var currentPlayer = playerXName
    var xScore = 0
    var oScore = 0

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == playerXName) playerOName else playerXName
    }

    fun checkWinner(state: IntArray): Boolean {
        return checkRows(state) || checkColumns(state) || checkDiagonals(state)
    }

    private fun checkRows(state: IntArray): Boolean {
        for (i in 0 until boardSize) {
            var sum = 0
            for (j in 0 until boardSize) {
                sum += state[i * boardSize + j]
            }
            if (abs(sum.toDouble()).toInt() == boardSize) return true
        }
        return false
    }

    private fun checkColumns(state: IntArray): Boolean {
        for (i in 0 until boardSize) {
            var sum = 0
            for (j in 0 until boardSize) {
                sum += state[j * boardSize + i]
            }
            if (abs(sum.toDouble()).toInt() == boardSize) return true
        }
        return false
    }

    private fun checkDiagonals(state: IntArray): Boolean {
        var sumMain = 0
        var sumAnti = 0
        for (i in 0 until boardSize) {
            sumMain += state[i * boardSize + i]
            sumAnti += state[(i + 1) * (boardSize - 1)]
        }
        return abs(sumMain.toDouble()).toInt() == boardSize || abs(sumAnti.toDouble()).toInt() == boardSize
    }

    fun isBoardFull(state: IntArray): Boolean {
        for (cell in state) {
            if (cell == 0) {
                return false
            }
        }
        return true
    }

    fun incrementScore() {
        if (currentPlayer == playerXName) {
            xScore++
        } else {
            oScore++
        }
    }

    fun reset() {
        currentPlayer = playerXName
        xScore = 0
        oScore = 0
    }
}

