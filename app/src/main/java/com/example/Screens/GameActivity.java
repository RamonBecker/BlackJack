package com.example.Screens;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.entities.Game;
import br.com.entities.StoreUser;
import br.com.entities.User;

public class GameActivity extends BasicMenuAcitivy {


    // Cards Computer
    ImageView imgCardComputer1, imgCardComputer2, imgCardComputer3;

    //Cards User
    ImageView imgCardUser1, imgCardUser2, imgCardUser3, imgCardUser4, imgCardUser5;


    TextView contWin, contLoss, contSumCards;

    Button buttonHint, buttonStand;

    private ArrayList<ImageView> listCards;

    private ArrayList<Integer> listIdCards;

    //Numbers of plays
    private int playsUser = 0;
    private int playsComputer = 0;

    private int contImageViewUser = 2;
    private int contImageViewComputer = 0;

    private User user;

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        contWin = (TextView) findViewById(R.id.textViewContadorVitoria);
        contLoss = (TextView) findViewById(R.id.textViewContadorDerrota);
        contSumCards = (TextView) findViewById(R.id.textViewSumCards);

        //Cards Computer
        imgCardComputer1 = findViewById(R.id.imageViewCardPC1);
        imgCardComputer2 = findViewById(R.id.imageViewCardPC2);


        //Cards User

        imgCardUser1 = findViewById(R.id.imageViewCard1);
        imgCardUser2 = findViewById(R.id.imageViewCard2);
        imgCardUser3 = findViewById(R.id.imageViewCard3);
        imgCardUser4 = findViewById(R.id.imageViewCard4);
        imgCardUser5 = findViewById(R.id.imageViewCard5);

        //Add img computer e user
        listCards = new ArrayList<>();

        listCards.add(imgCardComputer1);
        listCards.add(imgCardComputer2);


        listCards.add(imgCardUser1);
        listCards.add(imgCardUser2);
        listCards.add(imgCardUser3);
        listCards.add(imgCardUser4);
        listCards.add(imgCardUser5);

        setCardsImage();

        buttonHint = (Button) findViewById(R.id.buttonPurchase);
        buttonStand = (Button) findViewById(R.id.buttonStand);


        StoreUser storeUser = StoreUser.getInstance();

        user = storeUser.checkIsActive();
        getSupportActionBar().setTitle(user.getName());


        //instance to Game
        game = new Game();

        //Add IDS of cards
        listIdCards = new ArrayList<>();

        listIdCards.add(R.drawable.asdepaus);
        listIdCards.add(R.drawable.doisdepaus);
        listIdCards.add(R.drawable.tresouros);
        listIdCards.add(R.drawable.quatrodeouros);
        listIdCards.add(R.drawable.cincodeespadas);
        listIdCards.add(R.drawable.seisdeespadas);
        listIdCards.add(R.drawable.setedecopas);
        listIdCards.add(R.drawable.oitodecopas);
        listIdCards.add(R.drawable.novedeouros);
        listIdCards.add(R.drawable.damadepaus);
        listIdCards.add(R.drawable.dezouros);
        listIdCards.add(R.drawable.valetedepaus);
        listIdCards.add(R.drawable.reideespadas);

        View view = null;

        playComputer();

        actionButtonHold(null);
        actionButtonHold(null);

        Log.i("ganhador", String.valueOf(game.resultGame(playsUser, playsComputer)));

        setTextWinLoss();
    }


    public void actionStand(View view) {
        buttonHint.setEnabled(false);
        Game.checkStand = true;

        listCards.get(0).setVisibility(View.VISIBLE);
        Log.i("USER:", String.valueOf(playsUser));
        Log.i("PC:", String.valueOf(playsComputer));
        Log.i("ganhador", String.valueOf(game.resultGame(playsUser, playsComputer)));
        Log.i("user", user.toString());
        setTextWinLoss();

    }

    private void setTextWinLoss(){
        contLoss.setText(String.valueOf(user.getLoss()));
        contWin.setText(String.valueOf(user.getWin()));
    }


    public void actionButtonHold(View view) {

        int id = game.generateCards();

        for (int i = 0; i < listIdCards.size(); i++) {
            if (i == id) {
                listCards.get(contImageViewUser).setImageResource(listIdCards.get(i));
            }
        }

        if (id >= 10) {
            playsUser += 10;
        } else {
            playsUser += id + 1;
        }

        contImageViewUser++;

        if (contImageViewUser == 8) {
            contImageViewUser = 3;
        }

        contSumCards.setText(String.valueOf(playsUser));
        setTextWinLoss();
        Log.i("ganhador", String.valueOf(game.resultGame(playsUser, playsComputer)));
    }


    private void playComputer() {

        int id1 = game.generateCards();
        int id2 = game.generateCards();

        for (int i = 0; i < listIdCards.size(); i++) {
            if (i == id2) {
                listCards.get(1).setImageResource(listIdCards.get(i));
            }
        }

        if (id1 >= 10) {
            playsComputer += 10;
        } else {
            playsComputer += id1 + 1;
        }

        if (id2 >= 10) {
            playsComputer += 10;
        } else {
            playsComputer += id2 + 1;
        }
    }

    // Set Cards Computer and set cards user
    private void setCardsImage() {

        for (ImageView img : listCards) {
            img.setImageResource(R.drawable.verso);
        }
    }

}
