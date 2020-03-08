package br.com.entities;

import android.util.Log;

import java.util.Random;

public class Game {

    private Random random;

    public static boolean checkStand = false;
    public  static  boolean winUser = false;
    public  static boolean lossUser = false;
    public  static boolean draw = false;
    public static boolean burst = false;

    public int generateCards() {
        random = new Random();
        return random.nextInt(13);
    }

    public int checkPlays(int playUser, int playComputer) {
        int result = 50;

        // 0 - User Win
        // 1 - Draw
        // 2 - Computer Win
        // 3 - loss playUser e computer

        if (playUser == 21 && playComputer < 21) {

            result = 0;
        } else if (playUser == 21 && playComputer == 21) {
            winUser = false;
            lossUser = false;
            draw = true;
            result = 1;
        } else if (playComputer == 21 && playUser < 21) {
            winUser = false;
            lossUser = true;
            result = 2;
        } else if (playUser > 21 && playComputer > 21) {
            burst = true;
            result = 3;

        }

        return result;
    }

    public int checkStand(int playUser, int playComputer) {
        int result = 50;

        if (playUser < 21 && playComputer < 21) {
            if (playUser > playComputer) {
                winUser = true;
                lossUser = false;
                result = 0;
            } else if (playComputer == playUser) {
                draw = true;
                result = 1;
            } else {
                winUser = false;
                lossUser = true;
                result = 2;
            }

        }
        return result;
    }

    public String resultGame(int playUser, int playComputer) {
        int result = 50;

        if (checkStand) {
            result = checkStand(playUser, playComputer);
            checkStand = false;
        } else {
            result = checkPlays(playUser, playComputer);
        }

        if (result == 0) {
            winUser = true;
            lossUser = false;
            return "Voce fez BlackJack";
        }

        if (result == 1) {
            return "Empate";
        }

        if (result == 2) {

            return "A mesa fez BlackJack";
        }

        if (result == 3) {
            return "Empate";
        }


        return "";

    }
}
