package com.example.android_tic_tac_toe_app

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class FourTileActivity : AppCompatActivity() {
    private var board: Board? = null
    private var gameLogic: GameLogic? = null
    private var scoreX: TextView? = null
    private var scoreO: TextView? = null
    private var resetButton: Button? = null
    companion object {
        const val PLAYER_X_NAME = "Joomeng"
        const val PLAYER_O_NAME = "Moodeng"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.four_tile)

        board = Board(this, 4) // Initialize 4x4 board
        gameLogic = GameLogic(4, PLAYER_X_NAME, PLAYER_O_NAME)

        scoreX = findViewById(R.id.ScoreX)
        scoreO = findViewById(R.id.ScoreY)
        resetButton = findViewById(R.id.Reset)

        initializeBoard()
        setupResetButton()
    }

    private fun initializeBoard() {
        board?.buttons?.forEachIndexed { index, button ->
            button?.setOnClickListener { onTileClick(index) }
        }
    }

    private fun setupResetButton() {
        resetButton?.setOnClickListener { resetGame() }
    }

    private fun onTileClick(index: Int) {
        if (board?.isTileEmpty(index) == true) {
            board?.updateTile(index, gameLogic?.currentPlayer ?: "")

            if (gameLogic?.checkWinner(board?.state ?: IntArray(0)) == true) {
                showWinnerDialog(gameLogic?.currentPlayer ?: "")
                gameLogic?.incrementScore()
                updateScore()
            } else if (gameLogic?.isBoardFull(board?.state ?: IntArray(0)) == true) {
                showDrawDialog()
            } else {
                gameLogic?.switchPlayer()
            }
        }
    }

    private fun resetGame() {
        board?.reset()
        gameLogic?.reset()
        updateScore()
    }

    @SuppressLint("SetTextI18n")
    private fun updateScore() {
        scoreX?.text = "Score ${PLAYER_X_NAME}: ${gameLogic?.xScore}"
        scoreO?.text = "Score ${PLAYER_O_NAME}: ${gameLogic?.oScore}"
    }

    private fun showWinnerDialog(player: String) {
        val winnerName = if (player == PLAYER_X_NAME) PLAYER_X_NAME else PLAYER_O_NAME
        showAlert("$winnerName Wins!")

    }

    private fun showDrawDialog() {
        showAlert("It's a Draw!")
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ -> board?.reset() }
            .show()
    }
}
