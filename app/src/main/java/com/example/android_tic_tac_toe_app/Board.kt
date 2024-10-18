package com.example.android_tic_tac_toe_app

import android.app.Activity
import android.widget.ImageView

class Board(activity: Activity, private val boardSize: Int) {
    var buttons: Array<ImageView?>
    val state: IntArray

    init {
        val totalTiles = boardSize * boardSize
        buttons = arrayOfNulls(totalTiles)
        state = IntArray(totalTiles)

        for (i in 0 until totalTiles) {
            val id = activity.resources.getIdentifier("tile" + (i + 1), "id", activity.packageName)
            buttons[i] = activity.findViewById(id)
        }
        reset()
    }

    fun isTileEmpty(index: Int): Boolean {
        return state[index] == 0
    }

    fun updateTile(index: Int, player: String) {
        if (player == "Joomeng") { // Changed from "X"
            buttons[index]!!.setImageResource(R.drawable.joomeng)
            state[index] = 1
        } else { //
            buttons[index]!!.setImageResource(R.drawable.moodeng)
            state[index] = -1
        }
    }

    fun reset() {
        for (button in buttons) {
            button!!.setImageDrawable(null)
        }
        for (i in state.indices) {
            state[i] = 0
        }
    }
}
