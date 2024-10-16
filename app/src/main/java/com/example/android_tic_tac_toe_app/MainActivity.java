package com.example.android_tic_tac_toe_app;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Board board;
    private GameLogic gameLogic;
    private TextView scoreX, scoreO;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_tile);

        board = new Board(this, 4); // Initialize 4x4 board
        gameLogic = new GameLogic(4); // Initialize 4x4 logic

        scoreX = findViewById(R.id.ScoreX);
        scoreO = findViewById(R.id.ScoreY);
        resetButton = findViewById(R.id.Reset);

        initializeBoard();
        setupResetButton();
    }

    private void initializeBoard() {
        for (int i = 0; i < board.buttons.length; i++) {
            final int index = i;
            board.buttons[i].setOnClickListener(view -> onTileClick(index));
        }
    }

    private void setupResetButton() {
        resetButton.setOnClickListener(view -> resetGame());
    }

    private void onTileClick(int index) {
        if (board.isTileEmpty(index)) {
            board.updateTile(index, gameLogic.getCurrentPlayer());
            if (gameLogic.checkWinner(board.getState())) {
                showWinnerDialog(gameLogic.getCurrentPlayer());
                gameLogic.incrementScore();
                updateScore();
            } else if (gameLogic.isBoardFull(board.getState())) {
                showDrawDialog();
            } else {
                gameLogic.switchPlayer();
            }
        }
    }

    private void resetGame() {
        board.reset();
        gameLogic.reset();
        updateScore();
    }

    private void updateScore() {
        scoreX.setText("Score X: " + gameLogic.getXScore());
        scoreO.setText("Score O: " + gameLogic.getOScore());
    }

    private void showWinnerDialog(String player) {
        showAlert(player + " Wins!");
    }

    private void showDrawDialog() {
        showAlert("It's a Draw!");
    }

    private void showAlert(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, which) -> board.reset())
                .show();
    }
}
