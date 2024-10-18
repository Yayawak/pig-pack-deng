package com.example.android_tic_tac_toe_app

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.four_tile)

        board = Board(this, 4) // Initialize 4x4 board
        gameLogic = GameLogic(4) // Initialize 4x4 logic

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

    private fun updateScore() {
        scoreX?.text = "Score X: ${gameLogic?.xScore}"
        scoreO?.text = "Score O: ${gameLogic?.oScore}"
    }

    private fun showWinnerDialog(player: String) {
        showAlert("$player Wins!")
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
