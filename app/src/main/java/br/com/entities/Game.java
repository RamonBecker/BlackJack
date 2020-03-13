package br.com.entities;

import android.util.Log;

import java.util.Random;

public class Game {

    private Random random;

    public static boolean checkStand = false;

    public int generateCards() {
        random = new Random();
        return random.nextInt(12);
    }

    public int checkPlays(int playUser, int playComputer) {
        int result = 50;

        // 0 - User Win
        // 1 - Draw
        // 2 - Computer Win
        // 3 - loss playUser e computer
        // 4 - burst user

        if (playUser == 21 && playComputer < 21) {

            result = 0;
        } else if (playUser == 21 && playComputer == 21) {
            result = 1;
        } else if (playComputer == 21 && playUser < 21) {

            result = 2;
        } else if (playUser > 21 && playComputer > 21) {
            result = 3;
        } else if (playComputer <= 21 && playUser > 21) {
            result = 4;

        } else if (playUser <= 21 && playComputer > 21) {
            result = 0;
        }
        return result;
    }

    public int checkStand(int playUser, int playComputer) {
        int result = 40;

        if (playUser < 21 && playComputer < 21) {
            if (playUser > playComputer) {
                result = 0;
            }
            if (playComputer == playUser) {
                result = 1;
            }
            if (playComputer > playUser) {
                result = 2;
            }

        }
        return result;
    }

    public int resultGame(int playUser, int playComputer) {
        int result = 50;
        //
        if (checkStand) {
            result = checkStand(playUser, playComputer);
            if (result == 40) {
                result = checkPlays(playUser, playComputer);
            }
            Log.i("chechStand", "CheckStand");
            return result;
        }

        result = checkPlays(playUser, playComputer);
        return result;
    }
}
