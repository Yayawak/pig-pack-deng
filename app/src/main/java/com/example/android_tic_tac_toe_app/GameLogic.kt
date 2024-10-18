package com.example.android_tic_tac_toe_app;

public class GameLogic {
    private String currentPlayer;
    private int xScore, oScore;
    private final int boardSize;

    public GameLogic(int size) {
        boardSize = size;
        currentPlayer = "X";
        xScore = 0;
        oScore = 0;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    public boolean checkWinner(int[] state) {
        return checkRows(state) || checkColumns(state) || checkDiagonals(state);
    }

    private boolean checkRows(int[] state) {
        for (int i = 0; i < boardSize; i++) {
            int sum = 0;
            for (int j = 0; j < boardSize; j++) {
                sum += state[i * boardSize + j];
            }
            if (Math.abs(sum) == boardSize) return true;
        }
        return false;
    }

    private boolean checkColumns(int[] state) {
        for (int i = 0; i < boardSize; i++) {
            int sum = 0;
            for (int j = 0; j < boardSize; j++) {
                sum += state[j * boardSize + i];
            }
            if (Math.abs(sum) == boardSize) return true;
        }
        return false;
    }

    private boolean checkDiagonals(int[] state) {
        int sumMain = 0, sumAnti = 0;
        for (int i = 0; i < boardSize; i++) {
            sumMain += state[i * boardSize + i];
            sumAnti += state[(i + 1) * (boardSize - 1)];
        }
        return Math.abs(sumMain) == boardSize || Math.abs(sumAnti) == boardSize;
    }

    public boolean isBoardFull(int[] state) {
        for (int cell : state) {
            if (cell == 0) {
                return false;
            }
        }
        return true;
    }

    public void incrementScore() {
        if (currentPlayer.equals("X")) {
            xScore++;
        } else {
            oScore++;
        }
    }

    public int getXScore() {
        return xScore;
    }

    public int getOScore() {
        return oScore;
    }

    public void reset() {
        currentPlayer = "X";
    }
}
