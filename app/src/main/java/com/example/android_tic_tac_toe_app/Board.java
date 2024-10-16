package com.example.android_tic_tac_toe_app;

import android.app.Activity;
import android.widget.ImageView;

public class Board {
    public ImageView[] buttons;
    private int[] state;
    private final int boardSize;

    public Board(Activity activity, int size) {
        boardSize = size;
        int totalTiles = size * size;
        buttons = new ImageView[totalTiles];
        state = new int[totalTiles];

        for (int i = 0; i < totalTiles; i++) {
            int id = activity.getResources().getIdentifier("tile" + (i + 1), "id", activity.getPackageName());
            buttons[i] = activity.findViewById(id);
        }
        reset();
    }

    public boolean isTileEmpty(int index) {
        return state[index] == 0;
    }

    public void updateTile(int index, String player) {
        if (player.equals("X")) {
            buttons[index].setImageResource(R.drawable.cross_app);
            state[index] = 1;
        } else {
            buttons[index].setImageResource(R.drawable.circle_app);
            state[index] = -1;
        }
    }

    public int[] getState() {
        return state;
    }

    public void reset() {
        for (ImageView button : buttons) {
            button.setImageDrawable(null);
        }
        for (int i = 0; i < state.length; i++) {
            state[i] = 0;
        }
    }
}
