package br.com.entities;

import java.util.Random;

public class Game {

    private Random random;

    public int generateCards(){
        random = new Random();
        return random.nextInt(13);
    }

    public int checkPlays(int playUser, int playComputer){
        int result = 50;

        // 0 - User Win
        // 1 - Draw
        // 2 - Computer Win
        // 3 - loss playUser e computer

        if(playUser == 21 && playComputer < 21){
            result = 0;
        }
        else if(playUser == 21 && playComputer == 21){
            result  = 1;
        }
        else if(playComputer == 21 && playUser < 21){
            result = 2;
        }
        else if(playUser > 21 && playComputer > 21){
            result = 3;
        }
        return result;
    }
}
